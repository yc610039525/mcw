package cn.cd.caoyeung.webapp.utils.lang;

public class StrResemble {
	public enum DIRECTION {
		TOP, TOP_LEFT, LEFT
	};

	private char[] first;
	private char[] second;
	private int[][] lcsTable;
	private DIRECTION[][] lcsAssistTable;
	private int lcsLength;
	private String lcs_str, lcsMatrix_str, lcsAssistMatrix_str;
	private StringBuffer str_buffer;

	private int q;//相似字符串长度
	private int s;//字符串1的不相似长度
	private int r;//字符串2的不相似长度
	private double likedNum;//

	public StrResemble(String str1, String str2) {
		first = str1.toCharArray();
		second = str2.toCharArray();
		lcsTable = new int[first.length + 1][second.length + 1];
		lcsAssistTable = new DIRECTION[first.length + 1][second.length + 1];
		lcs_str = null;
		str_buffer = new StringBuffer();
	}

	//判断相似度，并写入相似度
	public double judgeLiked() {
		getLCSLength();
		runLCS();
		q = getLCSLength();
		s = first.length - q;
		r = second.length - q;
		likedNum = (q * 100) / (q + s + r);
		return likedNum;
	}

	public static void main(String[] args) {
		String a = "我抄我抄我抄抄抄：明月几时有，把酒问青天，不知天上宫阙，今夕是何年";
		String b = "苏轼曾经抄写过“明月几时有，把酒问青天”的千古名句";
		StrResemble lcs = new StrResemble(a, b);
		lcs.judgeLiked();
		println("相似度：" + lcs.getLikedNum());
	}

	public int getLCSLength() {
		lcsLength = getLCSLength(first, second);
		return lcsLength;
	}

	private int getLCSLength(char[] one, char[] two) {
		lcsTable = new int[one.length + 1][two.length + 1];
		lcsAssistTable = new DIRECTION[one.length + 1][two.length + 1];

		for (int i = 0; i < one.length; i++) {
			lcsTable[i][0] = 0;
		}

		for (int j = 0; j < two.length - 1; j++) {
			lcsTable[0][j] = 0;
		}

		for (int i = 0; i < one.length; i++) {
			for (int j = 0; j < two.length; j++) {
				if (one[i] == two[j]) {
					lcsTable[i + 1][j + 1] = lcsTable[i][j] + 1;
					lcsAssistTable[i + 1][j + 1] = DIRECTION.TOP_LEFT;
				} else if (lcsTable[i][j + 1] >= lcsTable[i + 1][j]) {
					lcsTable[i + 1][j + 1] = lcsTable[i][j + 1];
					lcsAssistTable[i + 1][j + 1] = DIRECTION.TOP;
				} else {
					lcsTable[i + 1][j + 1] = lcsTable[i + 1][j];
					lcsAssistTable[i + 1][j + 1] = DIRECTION.LEFT;
				}
			}
		}

		lcsLength = lcsTable[one.length][two.length];
		return lcsLength;
	}

	public void runLCS() {
		runLCS(lcsAssistTable, first, first.length, second.length);
		lcs_str = str_buffer.toString();
	}

	private void runLCS(DIRECTION[][] lcsAssistTable, char[] one,
			int oneLength, int twoLength) {
		if (oneLength == 0 || twoLength == 0) {
			return;
		}

		int i = oneLength;
		int j = twoLength;

		if (lcsAssistTable[i][j] == DIRECTION.TOP_LEFT) {
			runLCS(lcsAssistTable, one, i - 1, j - 1);
			str_buffer.append(one[i - 1]);
		} else if (lcsAssistTable[i][j] == DIRECTION.TOP) {
			runLCS(lcsAssistTable, one, i - 1, j);
		} else {
			runLCS(lcsAssistTable, one, i, j - 1);
		}

	}

	public String getLCSAssistMatrixString() {
		str_buffer = new StringBuffer();
		for (DIRECTION[] row : lcsAssistTable) {
			for (DIRECTION element : row) {
				if (element == DIRECTION.LEFT) {
					str_buffer.append("");
				} else if (element == DIRECTION.TOP) {
					str_buffer.append(" ");
				} else if (element == DIRECTION.TOP_LEFT) {
					str_buffer.append("");
				} else {
					//str_buffer.append( "\t" );
				}
			}
			str_buffer.append("\n");
		}
		lcsAssistMatrix_str = str_buffer.toString();

		return lcsAssistMatrix_str;
	}

	public String getLCSMatrixString() {
		str_buffer = new StringBuffer();
		for (int[] row : lcsTable) {
			for (int element : row) {
				str_buffer.append(element + " ");
			}
			str_buffer.append("\n");
		}
		lcsMatrix_str = str_buffer.toString();
		return lcsMatrix_str;
	}

	public static void print(Object o) {
		System.out.print(o);
	}

	public static void println(Object o) {
		System.out.println(o);
	}

	public String getLCS() {
		return lcs_str;
	}

	/**
	 * @return first
	 */
	public char[] getFirstCharArray() {
		return first;
	}

	/**
	 * @return second
	 */
	public char[] getSecondCharArray() {
		return second;
	}

	/**
	 * @return lcsAssistTable
	 */
	public DIRECTION[][] getLcsAssistTable() {
		return lcsAssistTable;
	}

	/**
	 * @return lcsTable
	 */
	public int[][] getLcsTable() {
		return lcsTable;
	}

	public double getLikedNum() {
		return likedNum;
	}

	public void setLikedNum(double likedNum) {
		this.likedNum = likedNum;
	}

	public int getQ() {
		return q;
	}

	public void setQ(int q) {
		this.q = q;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}
}
