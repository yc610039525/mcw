package cn.cd.caoyeung.webapp.utils.file;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.PathMatcher;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

public class PathMatchingResourcePatternResolver {
	private static final Log logger = LogFactory.getLog(PathMatchingResourcePatternResolver.class);
	private PathMatcher pathMatcher = new AntPathMatcher();
	/**"classpath*:"*/
	String CLASSPATH_ALL_URL_PREFIX = "classpath*:";
	private final ResourceLoader resourceLoader;
	private static Method equinoxResolveMethod;
	static {
		try {
			Class<?> fileLocatorClass = PathMatchingResourcePatternResolver.class.getClassLoader().loadClass(
					"org.eclipse.core.runtime.FileLocator");
			equinoxResolveMethod = fileLocatorClass.getMethod("resolve", URL.class);
			logger.debug("Found Equinox FileLocator for OSGi bundle URL resolution");
		}
		catch (Throwable ex) {
			equinoxResolveMethod = null;
		}
	}
	public ResourceLoader getResourceLoader() {
		return this.resourceLoader;
	}
	public PathMatchingResourcePatternResolver() {
		this.resourceLoader = new DefaultResourceLoader();
	}
	public PathMatcher getPathMatcher() {
		return this.pathMatcher;
	}
	/**
	 * resource对应URL协议以jar或zip开头
	 * @param resource
	 * @return
	 * @throws IOException
	 */
	protected boolean isJarResource(Resource resource) throws IOException {
		return ResourceUtils.isJarURL(resource.getURL());
	}
	
	public ClassLoader getClassLoader() {
		return getResourceLoader().getClassLoader();
	}

	/**
	 * 根据 locationPattern 查找资源
	 * @param locationPattern
	 * @return
	 * @throws IOException
	 */
	protected Resource[] findPathMatchingResources(String locationPattern) throws IOException {
		String rootDirPath = determineRootDir(locationPattern);
		String subPattern = locationPattern.substring(rootDirPath.length());
		Resource[] rootDirResources = getResources(rootDirPath);
		Set<Resource> result = new LinkedHashSet<Resource>(16);
		for (Resource rootDirResource : rootDirResources) {
			rootDirResource = resolveRootDirResource(rootDirResource);
			if (isJarResource(rootDirResource)) {
				result.addAll(doFindPathMatchingJarResources(rootDirResource, subPattern));
			}
//			else if (rootDirResource.getURL().getProtocol().startsWith(ResourceUtils.URL_PROTOCOL_VFS)) {
//				result.addAll(VfsResourceMatchingDelegate.findMatchingResources(rootDirResource, subPattern, getPathMatcher()));
//			}
			else {
				result.addAll(doFindPathMatchingFileResources(rootDirResource, subPattern));
			}
		}
		return result.toArray(new Resource[result.size()]);
	}
	/**
	 * 获取资源
	 * @param locationPattern
	 * @return
	 * @throws IOException
	 */
	public Resource[] getResources(String locationPattern) throws IOException {
		Assert.notNull(locationPattern, "Location pattern must not be null");
		if (locationPattern.startsWith(CLASSPATH_ALL_URL_PREFIX)) {
			//模糊路径处理 * ?
			if (getPathMatcher().isPattern(locationPattern.substring(CLASSPATH_ALL_URL_PREFIX.length()))) {
				return findPathMatchingResources(locationPattern);
			}
			else {
				return findAllClassPathResources(locationPattern.substring(CLASSPATH_ALL_URL_PREFIX.length()));
			}
		}
		else {
			int prefixEnd = locationPattern.indexOf(":") + 1;
			if (getPathMatcher().isPattern(locationPattern.substring(prefixEnd))) {
				return findPathMatchingResources(locationPattern);
			} else {
				return new Resource[] {getResourceLoader().getResource(locationPattern)};
			}
		}
	}
	protected String determineRootDir(String location) {
		//"classpath:com/c/b/a/*.xml"
		int prefixEnd = location.indexOf(":") + 1;
		int rootDirEnd = location.length();
		while (rootDirEnd > prefixEnd && getPathMatcher().isPattern(location.substring(prefixEnd, rootDirEnd))) {
			rootDirEnd = location.lastIndexOf('/', rootDirEnd - 2) + 1;
		}
		if (rootDirEnd == 0) {
			rootDirEnd = prefixEnd;
		}
		return location.substring(0, rootDirEnd);
	}
	/**
	 * 匹配 location开头所有资源
	 * @param location
	 * @return
	 * @throws IOException
	 */
	protected Resource[] findAllClassPathResources(String location) throws IOException {
		String path = location;
		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		Enumeration<URL> resourceUrls = getClassLoader().getResources(path);
		Set<Resource> result = new LinkedHashSet<Resource>(16);
		while (resourceUrls.hasMoreElements()) {
			URL url = resourceUrls.nextElement();
			result.add(convertClassLoaderURL(url));
		}
		return result.toArray(new Resource[result.size()]);
	}
	protected Resource convertClassLoaderURL(URL url) {
		return new UrlResource(url);
	}
	/**
	 * Resource 资源对应的URL的协议以bundle开头
	 * @param original
	 * @return
	 * @throws IOException
	 */
	protected Resource resolveRootDirResource(Resource original) throws IOException {
		if (equinoxResolveMethod != null) {
			URL url = original.getURL();
			if (url.getProtocol().startsWith("bundle")) {
				return new UrlResource((URL) ReflectionUtils.invokeMethod(equinoxResolveMethod, null, url));
			}
		}
		return original;
	}
	/**
	 * 加载Jar资源
	 * @param rootDirResource
	 * @param subPattern
	 * @return
	 * @throws IOException
	 */
	protected Set<Resource> doFindPathMatchingJarResources(Resource rootDirResource, String subPattern)
	throws IOException {
		URLConnection con = rootDirResource.getURL().openConnection();
		JarFile jarFile;
		String jarFileUrl;
		String rootEntryPath;
		boolean newJarFile = false;
		if (con instanceof JarURLConnection) {
			JarURLConnection jarCon = (JarURLConnection) con;
			ResourceUtils.useCachesIfNecessary(jarCon);
			jarFile = jarCon.getJarFile();
			jarFileUrl = jarCon.getJarFileURL().toExternalForm();
			JarEntry jarEntry = jarCon.getJarEntry();
			rootEntryPath = (jarEntry != null ? jarEntry.getName() : "");
		} else {
			String urlFile = rootDirResource.getURL().getFile();
			int separatorIndex = urlFile.indexOf(ResourceUtils.JAR_URL_SEPARATOR);
			if (separatorIndex != -1) {
				jarFileUrl = urlFile.substring(0, separatorIndex);
				rootEntryPath = urlFile.substring(separatorIndex + ResourceUtils.JAR_URL_SEPARATOR.length());
				jarFile = getJarFile(jarFileUrl);
			}
			else {
				jarFile = new JarFile(urlFile);
				jarFileUrl = urlFile;
				rootEntryPath = "";
			}
			newJarFile = true;
		}
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Looking for matching resources in jar file [" + jarFileUrl + "]");
			}
			if (!"".equals(rootEntryPath) && !rootEntryPath.endsWith("/")) {
				rootEntryPath = rootEntryPath + "/";
			}
			Set<Resource> result = new LinkedHashSet<Resource>(8);
			//查找jar资源
			for (Enumeration<JarEntry> entries = jarFile.entries(); entries.hasMoreElements();) {
				JarEntry entry = entries.nextElement();
				String entryPath = entry.getName();
				if (entryPath.startsWith(rootEntryPath)) {
					String relativePath = entryPath.substring(rootEntryPath.length());
					if (getPathMatcher().match(subPattern, relativePath)) {
						result.add(rootDirResource.createRelative(relativePath));
					}
				}
			}
			return result;
		} finally {
			if (newJarFile) {
				jarFile.close();
			}
		}
	}
	/**
	 * @param jarFileUrl
	 * @return JarFile
	 * @throws IOException
	 */
	protected JarFile getJarFile(String jarFileUrl) throws IOException {
		if (jarFileUrl.startsWith(ResourceUtils.FILE_URL_PREFIX)) {
			try {
				return new JarFile(ResourceUtils.toURI(jarFileUrl).getSchemeSpecificPart());
			}
			catch (URISyntaxException ex) {
				return new JarFile(jarFileUrl.substring(ResourceUtils.FILE_URL_PREFIX.length()));
			}
		}
		else {
			return new JarFile(jarFileUrl);
		}
	}
	
	protected Set<Resource> doFindPathMatchingFileResources(Resource rootDirResource, String subPattern)
	throws IOException {
		File rootDir;
		try {
			rootDir = rootDirResource.getFile().getAbsoluteFile();
		}
		catch (IOException ex) {
			return Collections.emptySet();
		}
		return doFindMatchingFileSystemResources(rootDir, subPattern);
	}
	/**
	 * 递归查找文件
	 */
	protected Set<Resource> doFindMatchingFileSystemResources(File rootDir, String subPattern) throws IOException {
		Set<File> matchingFiles = retrieveMatchingFiles(rootDir, subPattern);
		Set<Resource> result = new LinkedHashSet<Resource>(matchingFiles.size());
		for (File file : matchingFiles) {
			result.add(new FileSystemResource(file));
		}
		return result;
	}
	protected Set<File> retrieveMatchingFiles(File rootDir, String pattern) throws IOException {
		if (!rootDir.exists()) {
			return Collections.emptySet();
		}
		if (!rootDir.isDirectory()) {
			return Collections.emptySet();
		}
		if (!rootDir.canRead()) {
			return Collections.emptySet();
		}
		String fullPattern = StringUtils.replace(rootDir.getAbsolutePath(), File.separator, "/");
		if (!pattern.startsWith("/")) {
			fullPattern += "/";
		}
		fullPattern = fullPattern + StringUtils.replace(pattern, File.separator, "/");
		Set<File> result = new LinkedHashSet<File>(8);
		doRetrieveMatchingFiles(fullPattern, rootDir, result);
		return result;
	}
	/**
	 * 递归解析文件
	 */
	protected void doRetrieveMatchingFiles(String fullPattern, File dir, Set<File> result) throws IOException {
		File[] dirContents = dir.listFiles();
		if (dirContents == null) {
			return;
		}
		for (File content : dirContents) {
			String currPath = StringUtils.replace(content.getAbsolutePath(), File.separator, "/");
			if (content.isDirectory() && getPathMatcher().matchStart(fullPattern, currPath + "/")) {
				if (!content.canRead()) {
				}
				else {
					doRetrieveMatchingFiles(fullPattern, content, result);
				}
			}
			if (getPathMatcher().match(fullPattern, currPath)) {
				result.add(content);
			}
		}
	}
}
