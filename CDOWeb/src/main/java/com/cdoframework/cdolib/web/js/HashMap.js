//----------------------------------------------------------------------------
//HashMap
function HashMap() {
	this.arrKey = new Array();
	this.arrValue = new Array();

	//检查Key是否存在
	this.exists = function (strKey) {
		strKey = strKey.toUpperCase();
		for (var i = 0; i < this.arrKey.length; i++) {
			if (this.arrKey[i] == strKey) {
				return true;
			}
		}
		return false;
	};
	this.length = function () {
		return this.arrKey.length;
	};

	//设置Key和Value
	this.put = function (strKey, objValue) {
		strKey = strKey.toUpperCase();
		for (var i = 0; i < this.arrKey.length; i++) {
			if (this.arrKey[i] == strKey) {
				this.arrValue[i] = objValue;
				return;
			}
		}
		this.arrKey[this.arrKey.length] = strKey;
		this.arrValue[this.arrValue.length] = objValue;
	};

	//获取指定Key的Value，如果Key不存在，返回null
	this.get = function (strKey) {
		strKey = strKey.toUpperCase();
		for (var i = 0; i < this.arrKey.length; i++) {
			if (this.arrKey[i] == strKey) {
				return this.arrValue[i];
			}
		}
		return null;
	};

	//删除一个Key
	this.remove = function (strKey) {
		strKey = strKey.toUpperCase();
		for (var i = 0; i < this.arrKey.length; i++) {
			if (this.arrKey[i] == strKey) {
				this.arrKey.splice(i, 1);
				this.arrValue.splice(i, 1);
				return;
			}
		}
	};

	//获取所有的Key数组
	this.getKeys = function () {
		return this.arrKey;
	};

	//获取所有的Value数组
	this.getValues = function () {
		return this.arrValue;
	};
}
