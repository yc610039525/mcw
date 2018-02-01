Ext.override(Ext.form.BasicForm, {
	getFieldValues : function(dirtyOnly){
        var o = {},
            n,
            key,
            val;
        this.items.each(function(fs) {
        	var fList = [];
        	if(fs.items && fs.items.length > 0) {
        		fs.items.each(function(subFs) {
        			if(!Ext.isEmpty(subFs.name)) {
        				fList.push(subFs);
        			}
        		});
        	}
            if(!Ext.isEmpty(fs.getName())){
            	fList.push(fs);
            }
        	Ext.each(fList, function(f){
        		if (!f.disabled && (dirtyOnly !== true || f.isDirty())) {
	                n = f.getName();
	                key = o[n];
	                val = f.getValue();
	
	                if(Ext.isDefined(key)){
	                    if(Ext.isArray(key)){
	                        o[n].push(val);
	                    }else{
	                        o[n] = [key, val];
	                    }
	                }else{
	                    o[n] = val;
	                }
	            }
        	});
        });
        return o;
    }
});
Ext.override(Ext.form.Field, {
	initComponent : function() {
		if(this.disabled == true){
			this.fieldLabel = "<font color='#999999'>"+this.fieldLabel+"</font>";
		}else if(this.readOnly == true) {
			this.fieldLabel = "<font color='#999999'>"+this.fieldLabel+"</font>";
		}else if(this.allowBlank == false) {
			this.fieldLabel = "<font color='red'>"+this.fieldLabel+"</font>";
		}
		Ext.form.Field.superclass.initComponent.call(this);
	}
});

Ext.override(Ext.form.TextArea, {
	setValue : function(v) {
		if(!Ext.isEmpty(v)) {
			v = trimToEmpty(v);
			v = v.replace(/<\/br>/g, '\n');
		}
		Ext.form.TextArea.superclass.setValue.call(this, v);
	}
});

Ext.override(Ext.grid.GridView, {
	onLayout : function(vw, vh){
		var g = this.grid;
		if(g.autoHeight){
			// 原本Ext设置的是visible，不会出现滚动条
			this.scroller.dom.style.overflow = 'auto';
			// 另外还要更新表头宽度，以便滚动位置同步
			if(this.innerHd){
				this.innerHd.style.width = (vw)+'px';
			}
		}
	}
});

Ext.override(Ext.grid.RowNumberer, {
	width : 28
});

Ext.onReady(function(){
	//屏蔽backspace键的浏览器后退功能
	Ext.EventManager.addListener(document, 'keydown', function(evt, el, o){
		if(evt.getKey() == Ext.EventObjectImpl.prototype.BACKSPACE) {
			if(el.tagName == 'INPUT' ||
				el.tagName == 'TEXTAREA' ||
				el.tagName == 'BUTTON' ||
				el.tagName == 'SUBMIT') {
				if(el.readOnly == true) {
					evt.preventDefault();
				}
				return false;
			}else {
				evt.preventDefault();
			}
		}
		//屏蔽浏览器alt命令
		if(evt.altKey) {
			evt.preventDefault();
		}
	})
});