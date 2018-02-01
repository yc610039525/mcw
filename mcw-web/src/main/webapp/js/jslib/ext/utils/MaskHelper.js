Ext.namespace('IRMS');
MaskHelper = function() {
	var maskBox = {};
	return {
		mask : function(el, msg) {
			if(Ext.isEmpty(msg)) {
				msg = "数据加载中，请稍候...";
			}
			if(Ext.isString(el)) {
				var el = Ext.get(el);
				el.mask(msg);
			}else {
				el.mask(msg);
			}
			maskBox[el.id] = el;
		},
		unmask : function(el) {
			if(Ext.isString(el)) {
				var el = Ext.get(el);
				el.unmask();
			}else {
				el.unmask();
			}
			delete maskBox[el.id];
		},
		unmaskAll : function() {
			for(var key in maskBox) {
				maskBox[key].unmask();
				delete maskBox[key]
			}
		}
	}
}();
MaskHelper = MaskHelper;