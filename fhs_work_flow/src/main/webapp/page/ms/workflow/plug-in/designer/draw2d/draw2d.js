);
	this.doctype = null;
	this.implementation = _623e;
	this.documentElement = null;
	this.all = [];
	this.nodeName = "#document";
	this.nodeType = DOMNode.DOCUMENT_NODE;
	this._id = 0;
	this._lastId = 0;
	this._parseComplete = false;
	this.ownerDocument = this;
	this._performingImportNodeOperation = false;
};
DOMDocument.prototype = new DOMNode;
DOMDocument.prototype.getDoctype = function DOMDocument_getDoctype() {
	return this.doctype;
};
DOMDocument.prototype.getImplementation = function DOMDocument_implementation() {
	return this.implementation;
};
DOMDocument.prototype.getDocumentElement = function DOMDocument_getDocumentElement() {
	return this.documentElement;
};
DOMDocument.prototype.createElement = function DOMDocument_createElement(_623f) {
	if (this.ownerDocument.implementation.errorChecking
			&& (!this.ownerDocument.implementation._isValidName(_623f))) {
		throw (new DOMException(DOMException.INVALID_CHARACTER_ERR));
	}
	var node = new DOMElement(this);
	node.tagName = _623f;
	node.nodeName = _623f;
	this.all[this.all.length] = node;
	return node;
};
DOMDocument.prototype.createDocumentFragment = function DOMDocument_createDocumentFragment() {
	var node = new DOMDocumentFragment(this);
	return node;
};
DOMDocument.prototype.createTextNode = function DOMDocument_createTextNode(data) {
	var node = new DOMText(this);
	node.data = data;
	node.nodeValue = data;
	node.length = data.length;
	return node;
};
DOMDocument.prototype.createComment = function DOMDocument_createComment(data) {
	var node = new DOMComment(this);
	node.data = data;
	node.nodeValue = data;
	node.length = data.length;
	return node;
};
DOMDocument.prototype.createCDATASection = function DOMDocument_createCDATASection(
		data) {
	var node = new DOMCDATASection(this);
	node.data = data;
	node.nodeValue = data;
	node.length = data.length;
	return node;
};
DOMDocument.prototype.createProcessingInstruction = function DOMDocument_createProcessingInstruction(
		_6248, data) {
	if (this.ownerDocument.implementation.errorChecking
			&& (!this.implementation._isValidName(_6248))) {
		throw (new DOMException(DOMException.INVALID_CHARACTER_ERR));
	}
	var node = new DOMProcessingInstruction(this);
	node.target = _6248;
	node.nodeName = _6248;
	node.data = data;
	node.nodeValue = data;
	node.length = data.length;
	return node;
};
DOMDocument.prototype.createAttribute = function DOMDocument_createAttribute(
		name) {
	if (this.ownerDocument.implementation.errorChecking
			&& (!this.ownerDocument.implementation._isValidName(name))) {
		throw (new DOMException(DOMException.INVALID_CHARACTER_ERR));
	}
	var node = new DOMAttr(this);
	node.name = name;
	node.nodeName = name;
	return node;
};
DOMDocument.prototype.createElementNS = function DOMDocument_createElementNS(
		_624d, _624e) {
	if (this.ownerDocument.implementation.errorChecking) {
		if (!this.ownerDocument._isValidNamespace(_624d, _624e)) {
			throw (new DOMException(DOMException.NAMESPACE_ERR));
		}
		if (!this.ownerDocument.implementation._isValidName(_624e)) {
			throw (new DOMException(DOMException.INVALID_CHARACTER_ERR));
		}
	}
	var node = new DOMElement(this);
	var qname = this.implementation._parseQName(_624e);
	node.nodeName = _624e;
	node.namespaceURI = _624d;
	node.prefix = qname.prefix;
	node.localName = qname.localName;
	node.tagName = _624e;
	this.all[this.all.length] = node;
	return node;
};
DOMDocument.prototype.createAttributeNS = function DOMDocument_createAttributeNS(
		_6251, _6252) {
	if (this.ownerDocument.implementation.errorChecking) {
		if (!this.ownerDocument._isValidNamespace(_6251, _6252, true)) {
			throw (new DOMException(DOMException.NAMESPACE_ERR));
		}
		if (!this.ownerDocument.implementation._isValidName(_6252)) {
			throw (new DOMException(DOMException.INVALID_CHARACTER_ERR));
		}
	}
	var node = new DOMAttr(this);
	var qname = this.implementation._parseQName(_6252);
	node.nodeName = _6252;
	node.namespaceURI = _6251;
	node.prefix = qname.prefix;
	node.localName = qname.localName;
	node.name = _6252;
	node.nodeValue = "";
	return node;
};
DOMDocument.prototype.createNamespace = function DOMDocument_createNamespace(
		_6255) {
	var node = new DOMNamespace(this);
	var qname = this.implementation._parseQName(_6255);
	node.nodeName = _6255;
	node.prefix = qname.prefix;
	node.localName = qname.localName;
	node.name = _6255;
	node.nodeValue = "";
	return node;
};
DOMDocument.prototype.getElementById = function DOMDocument_getElementById(
		_6258) {
	retNode = null;
	for ( var i = 0; i < this.all.length; i++) {
		var node = this.all[i];
		if ((node.id == _6258)
				&& (node._isAncestor(node.ownerDocument.documentElement))) {
			retNode = node;
			break;
		}
	}
	return retNode;
};
DOMDocument.prototype._genId = function DOMDocument__genId() {
	this._lastId += 1;
	return this._lastId;
};
DOMDocument.prototype._isValidNamespace = function DOMDocument__isValidNamespace(
		_625b, _625c, _625d) {
	if (this._performingImportNodeOperation == true) {
		return true;
	}
	var valid = true;
	var qName = this.implementation._parseQName(_625c);
	if (this._parseComplete == true) {
		if (qName.localName.indexOf(":") > -1) {
			valid = false;
		}
		if ((valid) && (!_625d)) {
			if (!_625b) {
				valid = false;
			}
		}
		if ((valid) && (qName.prefix == "")) {
			valid = false;
		}
	}
	if ((valid) && (qName.prefix == "xml")
			&& (_625b != "http://www.w3.org/XML/1998/namespace")) {
		valid = false;
	}
	return valid;
};
DOMDocument.prototype.toString = function DOMDocument_toString() {
	return "" + this.childNodes;
};
DOMElement = function(_6260) {
	this._class = addClass(this._class, "DOMElement");
	this.DOMNode = DOMNode;
	this.DOMNode(_6260);
	this.tagName = "";
	this.id = "";
	this.nodeType = DOMNode.ELEMENT_NODE;
};
DOMElement.prototype = new DOMNode;
DOMElement.prototype.getTagName = function DOMElement_getTagName() {
	return this.tagName;
};
DOMElement.prototype.getAttribute = function DOMElement_getAttribute(name) {
	var ret = "";
	var attr = this.attributes.getNamedItem(name);
	if (attr) {
		ret = attr.value;
	}
	return ret;
};
DOMElement.prototype.setAttribute = function DOMElement_setAttribute(name,
		value) {
	var attr = this.attributes.getNamedItem(name);
	if (!attr) {
		attr = this.ownerDocument.createAttribute(name);
	}
	var value = new String(value);
	if (this.ownerDocument.implementation.errorChecking) {
		if (attr._readonly) {
			throw (new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR));
		}
		if (!this.ownerDocument.implementation._isValidString(value)) {
			throw (new DOMException(DOMException.INVALID_CHARACTER_ERR));
		}
	}
	if (this.ownerDocument.implementation._isIdDeclaration(name)) {
		this.id = value;
	}
	attr.value = value;
	attr.nodeValue = value;
	if (value.length > 0) {
		attr.specified = true;
	} else {
		attr.specified = false;
	}
	this.attributes.setNamedItem(attr);
};
DOMElement.prototype.removeAttribute = function DOMElement_removeAttribute(name) {
	return this.attributes.removeNamedItem(name);
};
DOMElement.prototype.getAttributeNode = function DOMElement_getAttributeNode(
		name) {
	return this.attributes.getNamedItem(name);
};
DOMElement.prototype.setAttributeNode = function DOMElement_setAttributeNode(
		_6269) {
	if (this.ownerDocument.implementation._isIdDeclaration(_6269.name)) {
		this.id = _6269.value;
	}
	return this.attributes.setNamedItem(_6269);
};
DOMElement.prototype.removeAttributeNode = function DOMElement_removeAttributeNode(
		_626a) {
	if (this.ownerDocument.implementation.errorChecking && _626a._readonly) {
		throw (new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR));
	}
	var _626b = this.attributes._findItemIndex(_626a._id);
	if (this.ownerDocument.implementation.errorChecking && (_626b < 0)) {
		throw (new DOMException(DOMException.NOT_FOUND_ERR));
	}
	return this.attributes._removeChild(_626b);
};
DOMElement.prototype.getAttributeNS = function DOMElement_getAttributeNS(_626c,
		_626d) {
	var ret = "";
	var attr = this.attributes.getNamedItemNS(_626c, _626d);
	if (attr) {
		ret = attr.value;
	}
	return ret;
};
DOMElement.prototype.setAttributeNS = function DOMElement_setAttributeNS(_6270,
		_6271, value) {
	var attr = this.attributes.getNamedItem(_6270, _6271);
	if (!attr) {
		attr = this.ownerDocument.createAttributeNS(_6270, _6271);
	}
	var value = new String(value);
	if (this.ownerDocument.implementation.errorChecking) {
		if (attr._readonly) {
			throw (new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR));
		}
		if (!this.ownerDocument._isValidNamespace(_6270, _6271)) {
			throw (new DOMException(DOMException.NAMESPACE_ERR));
		}
		if (!this.ownerDocument.implementation._isValidString(value)) {
			throw (new DOMException(DOMException.INVALID_CHARACTER_ERR));
		}
	}
	if (this.ownerDocument.implementation._isIdDeclaration(name)) {
		this.id = value;
	}
	attr.value = value;
	attr.nodeValue = value;
	if (value.length > 0) {
		attr.specified = true;
	} else {
		attr.specified = false;
	}
	this.attributes.setNamedItemNS(attr);
};
DOMElement.prototype.removeAttributeNS = function DOMElement_removeAttributeNS(
		_6274, _6275) {
	return this.attributes.removeNamedItemNS(_6274, _6275);
};
DOMElement.prototype.getAttributeNodeNS = function DOMElement_getAttributeNodeNS(
		_6276, _6277) {
	return this.attributes.getNamedItemNS(_6276, _6277);
};
DOMElement.prototype.setAttributeNodeNS = function DOMElement_setAttributeNodeNS(
		_6278) {
	if ((_6278.prefix == "")
			&& this.ownerDocument.implementation._isIdDeclaration(_6278.name)) {
		this.id = _6278.value;
	}
	return this.attributes.setNamedItemNS(_6278);
};
DOMElement.prototype.hasAttribute = function DOMElement_hasAttribute(name) {
	return this.attributes._hasAttribute(name);
};
DOMElement.prototype.hasAttributeNS = function DOMElement_hasAttributeNS(_627a,
		_627b) {
	return this.attributes._hasAttributeNS(_627a, _627b);
};
DOMElement.prototype.toString = function DOMElement_toString() {
	var ret = "";
	var ns = this._namespaces.toString();
	if (ns.length > 0) {
		ns = " " + ns;
	}
	var attrs = this.attributes.toString();
	if (attrs.length > 0) {
		attrs = " " + attrs;
	}
	ret += "<" + this.nodeName + ns + attrs + ">";
	ret += this.childNodes.toString();
	ret += "</" + this.nodeName + ">";
	return ret;
};
DOMAttr = function(_627f) {
	this._class = addClass(this._class, "DOMAttr");
	this.DOMNode = DOMNode;
	this.DOMNode(_627f);
	this.name = "";
	this.specified = false;
	this.value = "";
	this.nodeType = DOMNode.ATTRIBUTE_NODE;
	this.ownerElement = null;
	this.childNodes = null;
	this.attributes = null;
};
DOMAttr.prototype = new DOMNode;
DOMAttr.prototype.getName = function DOMAttr_getName() {
	return this.nodeName;
};
DOMAttr.prototype.getSpecified = function DOMAttr_getSpecified() {
	return this.specified;
};
DOMAttr.prototype.getValue = function DOMAttr_getValue() {
	return this.nodeValue;
};
DOMAttr.prototype.setValue = function DOMAttr_setValue(value) {
	if (this.ownerDocument.implementation.errorChecking && this._readonly) {
		throw (new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR));
	}
	this.setNodeValue(value);
};
DOMAttr.prototype.setNodeValue = function DOMAttr_setNodeValue(value) {
	this.nodeValue = new String(value);
	this.value = this.nodeValue;
	this.specified = (this.value.length > 0);
};
DOMAttr.prototype.toString = function DOMAttr_toString() {
	var ret = "";
	ret += this.nodeName + "=\"" + this.__escapeString(this.nodeValue) + "\"";
	return ret;
};
DOMAttr.prototype.getOwnerElement = function() {
	return this.ownerElement;
};
DOMNamespace = function(_6283) {
	this._class = addClass(this._class, "DOMNamespace");
	this.DOMNode = DOMNode;
	this.DOMNode(_6283);
	this.name = "";
	this.specified = false;
	this.value = "";
	this.nodeType = DOMNode.NAMESPACE_NODE;
};
DOMNamespace.prototype = new DOMNode;
DOMNamespace.prototype.getValue = function DOMNamespace_getValue() {
	return this.nodeValue;
};
DOMNamespace.prototype.setValue = function DOMNamespace_setValue(value) {
	this.nodeValue = new String(value);
	this.value = this.nodeValue;
};
DOMNamespace.prototype.toString = function DOMNamespace_toString() {
	var ret = "";
	if (this.nodeName != "") {
		ret += this.nodeName + "=\"" + this.__escapeString(this.nodeValue)
				+ "\"";
	} else {
		ret += "xmlns=\"" + this.__escapeString(this.nodeValue) + "\"";
	}
	return ret;
};
DOMCharacterData = function(_6286) {
	this._class = addClass(this._class, "DOMCharacterData");
	this.DOMNode = DOMNode;
	this.DOMNode(_6286);
	this.data = "";
	this.length = 0;
};
DOMCharacterData.prototype = new DOMNode;
DOMCharacterData.prototype.getData = function DOMCharacterData_getData() {
	return this.nodeValue;
};
DOMCharacterData.prototype.setData = function DOMCharacterData_setData(data) {
	this.setNodeValue(data);
};
DOMCharacterData.prototype.setNodeValue = function DOMCharacterData_setNodeValue(
		data) {
	if (this.ownerDocument.implementation.errorChecking && this._readonly) {
		throw (new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR));
	}
	this.nodeValue = new String(data);
	this.data = this.nodeValue;
	this.length = this.nodeValue.length;
};
DOMCharacterData.prototype.getLength = function DOMCharacterData_getLength() {
	return this.nodeValue.length;
};
DOMCharacterData.prototype.substringData = function DOMCharacterData_substringData(
		_6289, count) {
	var ret = null;
	if (this.data) {
		if (this.ownerDocument.implementation.errorChecking
				&& ((_6289 < 0) || (_6289 > this.data.length) || (count < 0))) {
			throw (new DOMException(DOMException.INDEX_SIZE_ERR));
		}
		if (!count) {
			ret = this.data.substring(_6289);
		} else {
			ret = this.data.substring(_6289, _6289 + count);
		}
	}
	return ret;
};
DOMCharacterData.prototype.appendData = function DOMCharacterData_appendData(
		arg) {
	if (this.ownerDocument.implementation.errorChecking && this._readonly) {
		throw (new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR));
	}
	this.setData("" + this.data + arg);
};
DOMCharacterData.prototype.insertData = function DOMCharacterData_insertData(
		_628d, arg) {
	if (this.ownerDocument.implementation.errorChecking && this._readonly) {
		throw (new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR));
	}
	if (this.data) {
		if (this.ownerDocument.implementation.errorChecking
				&& ((_628d < 0) || (_628d > this.data.length))) {
			throw (new DOMException(DOMException.INDEX_SIZE_ERR));
		}
		this.setData(this.data.substring(0, _628d).concat(arg,
				this.data.substring(_628d)));
	} else {
		if (this.ownerDocument.implementation.errorChecking && (_628d != 0)) {
			throw (new DOMException(DOMException.INDEX_SIZE_ERR));
		}
		this.setData(arg);
	}
};
DOMCharacterData.prototype.deleteData = function DOMCharacterData_deleteData(
		_628f, count) {
	if (this.ownerDocument.implementation.errorChecking && this._readonly) {
		throw (new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR));
	}
	if (this.data) {
		if (this.ownerDocument.implementation.errorChecking
				&& ((_628f < 0) || (_628f > this.data.length) || (count < 0))) {
			throw (new DOMException(DOMException.INDEX_SIZE_ERR));
		}
		if (!count || (_628f + count) > this.data.length) {
			this.setData(this.data.substring(0, _628f));
		} else {
			this.setData(this.data.substring(0, _628f).concat(
					this.data.substring(_628f + count)));
		}
	}
};
DOMCharacterData.prototype.replaceData = function DOMCharacterData_replaceData(
		_6291, count, arg) {
	if (this.ownerDocument.implementation.errorChecking && this._readonly) {
		throw (new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR));
	}
	if (this.data) {
		if (this.ownerDocument.implementation.errorChecking
				&& ((_6291 < 0) || (_6291 > this.data.length) || (count < 0))) {
			throw (new DOMException(DOMException.INDEX_SIZE_ERR));
		}
		this.setData(this.data.substring(0, _6291).concat(arg,
				this.data.substring(_6291 + count)));
	} else {
		this.setData(arg);
	}
};
DOMText = function(_6294) {
	this._class = addClass(this._class, "DOMText");
	this.DOMCharacterData = DOMCharacterData;
	this.DOMCharacterData(_6294);
	this.nodeName = "#text";
	this.nodeType = DOMNode.TEXT_NODE;
};
DOMText.prototype = new DOMCharacterData;
DOMText.prototype.splitText = function DOMText_splitText(_6295) {
	var data, inode;
	if (this.ownerDocument.implementation.errorChecking) {
		if (this._readonly) {
			throw (new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR));
		}
		if ((_6295 < 0) || (_6295 > this.data.length)) {
			throw (new DOMException(DOMException.INDEX_SIZE_ERR));
		}
	}
	if (this.parentNode) {
		data = this.substringData(_6295);
		inode = this.ownerDocument.createTextNode(data);
		if (this.nextSibling) {
			this.parentNode.insertBefore(inode, this.nextSibling);
		} else {
			this.parentNode.appendChild(inode);
		}
		this.deleteData(_6295);
	}
	return inode;
};
DOMText.prototype.toString = function DOMText_toString() {
	return this.__escapeString("" + this.nodeValue);
};
DOMCDATASection = function(_6297) {
	this._class = addClass(this._class, "DOMCDATASection");
	this.DOMCharacterData = DOMCharacterData;
	this.DOMCharacterData(_6297);
	this.nodeName = "#cdata-section";
	this.nodeType = DOMNode.CDATA_SECTION_NODE;
};
DOMCDATASection.prototype = new DOMCharacterData;
DOMCDATASection.prototype.splitText = function DOMCDATASection_splitText(_6298) {
	var data, inode;
	if (this.ownerDocument.implementation.errorChecking) {
		if (this._readonly) {
			throw (new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR));
		}
		if ((_6298 < 0) || (_6298 > this.data.length)) {
			throw (new DOMException(DOMException.INDEX_SIZE_ERR));
		}
	}
	if (this.parentNode) {
		data = this.substringData(_6298);
		inode = this.ownerDocument.createCDATASection(data);
		if (this.nextSibling) {
			this.parentNode.insertBefore(inode, this.nextSibling);
		} else {
			this.parentNode.appendChild(inode);
		}
		this.deleteData(_6298);
	}
	return inode;
};
DOMCDATASection.prototype.toString = function DOMCDATASection_toString() {
	var ret = "";
	ret += "<![CDATA[" + this.nodeValue + "]]>";
	return ret;
};
DOMComment = function(_629b) {
	this._class = addClass(this._class, "DOMComment");
	this.DOMCharacterData = DOMCharacterData;
	this.DOMCharacterData(_629b);
	this.nodeName = "#comment";
	this.nodeType = DOMNode.COMMENT_NODE;
};
DOMComment.prototype = new DOMCharacterData;
DOMComment.prototype.toString = function DOMComment_toString() {
	var ret = "";
	ret += "<!--" + this.nodeValue + "-->";
	return ret;
};
DOMProcessingInstruction = function(_629d) {
	this._class = addClass(this._class, "DOMProcessingInstruction");
	this.DOMNode = DOMNode;
	this.DOMNode(_629d);
	this.target = "";
	this.data = "";
	this.nodeType = DOMNode.PROCESSING_INSTRUCTION_NODE;
};
DOMProcessingInstruction.prototype = new DOMNode;
DOMProcessingInstruction.prototype.getTarget = function DOMProcessingInstruction_getTarget() {
	return this.nodeName;
};
DOMProcessingInstruction.prototype.getData = function DOMProcessingInstruction_getData() {
	return this.nodeValue;
};
DOMProcessingInstruction.prototype.setData = function DOMProcessingInstruction_setData(
		data) {
	this.setNodeValue(data);
};
DOMProcessingInstruction.prototype.setNodeValue = function DOMProcessingInstruction_setNodeValue(
		data) {
	if (this.ownerDocument.implementation.errorChecking && this._readonly) {
		throw (new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR));
	}
	this.nodeValue = new String(data);
	this.data = this.nodeValue;
};
DOMProcessingInstruction.prototype.toString = function DOMProcessingInstruction_toString() {
	var ret = "";
	ret += "<?" + this.nodeName + " " + this.nodeValue + " ?>";
	return ret;
};
DOMDocumentFragment = function(_62a1) {
	this._class = addClass(this._class, "DOMDocumentFragment");
	this.DOMNode = DOMNode;
	this.DOMNode(_62a1);
	this.nodeName = "#document-fragment";
	this.nodeType = DOMNode.DOCUMENT_FRAGMENT_NODE;
};
DOMDocumentFragment.prototype = new DOMNode;
DOMDocumentFragment.prototype.toString = function DOMDocumentFragment_toString() {
	var xml = "";
	var _62a3 = this.getChildNodes().getLength();
	for (intLoop = 0; intLoop < _62a3; intLoop++) {
		xml += this.getChildNodes().item(intLoop).toString();
	}
	return xml;
};
DOMDocumentType = function() {
	alert("DOMDocumentType.constructor(): Not Implemented");
};
DOMEntity = function() {
	alert("DOMEntity.constructor(): Not Implemented");
};
DOMEntityReference = function() {
	alert("DOMEntityReference.constructor(): Not Implemented");
};
DOMNotation = function() {
	alert("DOMNotation.constructor(): Not Implemented");
};
Strings = new Object();
Strings.WHITESPACE = " \t\n\r";
Strings.QUOTES = "\"'";
Strings.isEmpty = function Strings_isEmpty(strD) {
	return (strD === null) || (strD.length === 0);
};
Strings.indexOfNonWhitespace = function Strings_indexOfNonWhitespace(strD, iB,
		iE) {
	if (Strings.isEmpty(strD)) {
		return -1;
	}
	iB = iB || 0;
	iE = iE || strD.length;
	for ( var i = iB; i < iE; i++) {
		if (Strings.WHITESPACE.indexOf(strD.charAt(i)) == -1) {
			return i;
		}
	}
	return -1;
};
Strings.lastIndexOfNonWhitespace = function Strings_lastIndexOfNonWhitespace(
		strD, iB, iE) {
	if (Strings.isEmpty(strD)) {
		return -1;
	}
	iB = iB || 0;
	iE = iE || strD.length;
	for ( var i = iE - 1; i >= iB; i--) {
		if (Strings.WHITESPACE.indexOf(strD.charAt(i)) == -1) {
			return i;
		}
	}
	return -1;
};
Strings.indexOfWhitespace = function Strings_indexOfWhitespace(strD, iB, iE) {
	if (Strings.isEmpty(strD)) {
		return -1;
	}
	iB = iB || 0;
	iE = iE || strD.length;
	for ( var i = iB; i < iE; i++) {
		if (Strings.WHITESPACE.indexOf(strD.charAt(i)) != -1) {
			return i;
		}
	}
	return -1;
};
Strings.replace = function Strings_replace(strD, iB, iE, strF, strR) {
	if (Strings.isEmpty(strD)) {
		return "";
	}
	iB = iB || 0;
	iE = iE || strD.length;
	return strD.substring(iB, iE).split(strF).join(strR);
};
Strings.getLineNumber = function Strings_getLineNumber(strD, iP) {
	if (Strings.isEmpty(strD)) {
		return -1;
	}
	iP = iP || strD.length;
	return strD.substring(0, iP).split("\n").length;
};
Strings.getColumnNumber = function Strings_getColumnNumber(strD, iP) {
	if (Strings.isEmpty(strD)) {
		return -1;
	}
	iP = iP || strD.length;
	var arrD = strD.substring(0, iP).split("\n");
	var _62bb = arrD[arrD.length - 1];
	arrD.length--;
	var _62bc = arrD.join("\n").length;
	return iP - _62bc;
};
StringBuffer = function() {
	this._a = [];
};
StringBuffer.prototype.append = function StringBuffer_append(d) {
	this._a[this._a.length] = d;
};
StringBuffer.prototype.toString = function StringBuffer_toString() {
	return this._a.join("");
};
draw2d.XMLSerializer = function() {
	alert("do not init this class. Use the static methods instead");
};
draw2d.XMLSerializer.toXML = function(obj, _5717, _5718) {
	if (_5717 == undefined) {
		_5717 = "model";
	}
	_5718 = _5718 ? _5718 : "";
	var t = draw2d.XMLSerializer.getTypeName(obj);
	var s = _5718 + "<" + _5717 + " type=\"" + t + "\">";
	switch (t) {
	case "int":
	case "number":
	case "boolean":
		s += obj;
		break;
	case "string":
		s += draw2d.XMLSerializer.xmlEncode(obj);
		break;
	case "date":
		s += obj.toLocaleString();
		break;
	case "Array":
	case "array":
		s += "\n";
		var _571b = _5718 + "   ";
		for ( var i = 0; i < obj.length; i++) {
			s += draw2d.XMLSerializer.toXML(obj[i], ("element"), _571b);
		}
		s += _5718;
		break;
	default:
		if (obj !== null) {
			s += "\n";
			if (obj instanceof draw2d.ArrayList) {
				obj.trimToSize();
			}
			var _571d = obj.getPersistentAttributes();
			var _571b = _5718 + "   ";
			for ( var name in _571d) {
				s += draw2d.XMLSerializer.toXML(_571d[name], name, _571b);
			}
			s += _5718;
		}
		break;
	}
	s += "</" + _5717 + ">\n";
	return s;
};
draw2d.XMLSerializer.isSimpleVar = function(t) {
	switch (t) {
	case "int":
	case "string":
	case "String":
	case "Number":
	case "number":
	case "Boolean":
	case "boolean":
	case "bool":
	case "dateTime":
	case "Date":
	case "date":
	case "float":
		return true;
	}
	return false;
};
draw2d.XMLSerializer.getTypeName = function(obj) {
	if (obj === null) {
		return "undefined";
	}
	if (obj instanceof Array) {
		return "Array";
	}
	if (obj instanceof Date) {
		return "Date";
	}
	var t = typeof (obj);
	if (t == "number") {
		return (parseInt(obj).toString() == obj) ? "int" : "number";
	}
	if (draw2d.XMLSerializer.isSimpleVar(t)) {
		return t;
	}
	return obj.type.replace("@NAMESPACE" + "@", "");
};
draw2d.XMLSerializer.xmlEncode = function(_5722) {
	var _5723 = _5722;
	var amp = /&/gi;
	var gt = />/gi;
	var lt = /</gi;
	var quot = /"/gi;
	var apos = /'/gi;
	var _5729 = "&#62;";
	var _572a = "&#38;#60;";
	var _572b = "&#38;#38;";
	var _572c = "&#34;";
	var _572d = "&#39;";
	_5723 = _5723.replace(amp, _572b);
	_5723 = _5723.replace(quot, _572c);
	_5723 = _5723.replace(lt, _572a);
	_5723 = _5723.replace(gt, _5729);
	_5723 = _5723.replace(apos, _572d);
	return _5723;
};
draw2d.XMLDeserializer = function() {
	alert("do not init this class. Use the static methods instead");
};
draw2d.XMLDeserializer.fromXML = function(node, _4fe9) {
	var _4fea = "" + node.getAttributes().getNamedItem("type").getNodeValue();
	var value = node.getNodeValue();
	switch (_4fea) {
	case "int":
		try {
			return parseInt("" + node.getChildNodes().item(0).getNodeValue());
		} catch (e) {
			alert("Error:" + e + "\nDataType:" + _4fea + "\nXML Node:" + node);
		}
	case "string":
	case "String":
		try {
			if (node.getChildNodes().getLength() > 0) {
				return "" + node.getChildNodes().item(0).getNodeValue();
			}
			return "";
		} catch (e) {
			alert("Error:" + e + "\nDataType:" + _4fea + "\nXML Node:" + node);
		}
	case "Number":
	case "number":
		try {
			return parseFloat("" + node.getChildNodes().item(0).getNodeValue());
		} catch (e) {
			alert("Error:" + e + "\nDataType:" + _4fea + "\nXML Node:" + node);
		}
	case "Boolean":
	case "boolean":
	case "bool":
		try {
			return "true" == ("" + node.getChildNodes().item(0).getNodeValue())
					.toLowerCase();
		} catch (e) {
			alert("Error:" + e + "\nDataType:" + _4fea + "\nXML Node:" + node);
		}
	case "dateTime":
	case "Date":
	case "date":
		try {
			return new Date("" + node.getChildNodes().item(0).getNodeValue());
		} catch (e) {
			alert("Error:" + e + "\nDataType:" + _4fea + "\nXML Node:" + node);
		}
	case "float":
		try {
			return parseFloat("" + node.getChildNodes().item(0).getNodeValue());
		} catch (e) {
			alert("Error:" + e + "\nDataType:" + _4fea + "\nXML Node:" + node);
		}
		break;
	}
	_4fea = _4fea.replace("@NAMESPACE" + "@", "");
	var obj = eval("new " + _4fea + "()");
	if (_4fe9 != undefined && obj.setModelParent != undefined) {
		obj.setModelParent(_4fe9);
	}
	var _4fed = node.getChildNodes();
	for ( var i = 0; i < _4fed.length; i++) {
		var child = _4fed.item(i);
		var _4ff0 = child.getNodeName();
		if (obj instanceof Array) {
			_4ff0 = i;
		}
		obj[_4ff0] = draw2d.XMLDeserializer.fromXML(child,
				obj instanceof draw2d.AbstractObjectModel ? obj : _4fe9);
	}
	return obj;
};
draw2d.EditPolicy = function(_5cb1) {
	this.policy = _5cb1;
};
draw2d.EditPolicy.DELETE = "DELETE";
draw2d.EditPolicy.MOVE = "MOVE";
draw2d.EditPolicy.CONNECT = "CONNECT";
draw2d.EditPolicy.RESIZE = "RESIZE";
draw2d.EditPolicy.prototype.type = "draw2d.EditPolicy";
draw2d.EditPolicy.prototype.getPolicy = function() {
	return this.policy;
};
draw2d.AbstractPalettePart = function() {
	this.x = 0;
	this.y = 0;
	this.html = null;
};
draw2d.AbstractPalettePart.prototype.type = "draw2d.AbstractPalettePart";
draw2d.AbstractPalettePart.prototype = new draw2d.Draggable();
draw2d.AbstractPalettePart.prototype.createHTMLElement = function() {
	var item = document.createElement("div");
	item.id = this.id;
	item.style.position = "absolute";
	item.style.height = "24px";
	item.style.width = "24px";
	return item;
};
draw2d.AbstractPalettePart.prototype.setEnviroment = function(_6008, _6009) {
	this.palette = _6009;
	this.workflow = _6008;
};
draw2d.AbstractPalettePart.prototype.getHTMLElement = function() {
	if (this.html === null) {
		this.html = this.createHTMLElement();
		draw2d.Draggable.call(this, this.html);
	}
	return this.html;
};
draw2d.AbstractPalettePart.prototype.onDrop = function(_600a, _600b) {
	var _600c = this.workflow.getScrollLeft();
	var _600d = this.workflow.getScrollTop();
	var _600e = this.workflow.getAbsoluteX();
	var _600f = this.workflow.getAbsoluteY();
	this.setPosition(this.x, this.y);
	this.execute(_600a + _600c - _600e, _600b + _600d - _600f);
};
draw2d.AbstractPalettePart.prototype.execute = function(x, y) {
	alert("inerited class should override the method 'draw2d.AbstractPalettePart.prototype.execute'");
};
draw2d.AbstractPalettePart.prototype.setTooltip = function(_6012) {
	this.tooltip = _6012;
	if (this.tooltip !== null) {
		this.html.title = this.tooltip;
	} else {
		this.html.title = "";
	}
};
draw2d.AbstractPalettePart.prototype.setDimension = function(w, h) {
	this.width = w;
	this.height = h;
	if (this.html === null) {
		return;
	}
	this.html.style.width = this.width + "px";
	this.html.style.height = this.height + "px";
};
draw2d.AbstractPalettePart.prototype.setPosition = function(xPos, yPos) {
	this.x = Math.max(0, xPos);
	this.y = Math.max(0, yPos);
	if (this.html === null) {
		return;
	}
	this.html.style.left = this.x + "px";
	this.html.style.top = this.y + "px";
	this.html.style.cursor = "move";
};
draw2d.AbstractPalettePart.prototype.getWidth = function() {
	return this.width;
};
draw2d.AbstractPalettePart.prototype.getHeight = function() {
	return this.height;
};
draw2d.AbstractPalettePart.prototype.getY = function() {
	return this.y;
};
draw2d.AbstractPalettePart.prototype.getX = function() {
	return this.x;
};
draw2d.AbstractPalettePart.prototype.getPosition = function() {
	return new draw2d.Point(this.x, this.y);
};
draw2d.AbstractPalettePart.prototype.disableTextSelection = function(e) {
	if (typeof e.onselectstart != "undefined") {
		e.onselectstart = function() {
			return false;
		};
	} else {
		if (typeof e.style.MozUserSelect != "undefined") {
			e.style.MozUserSelect = "none";
		}
	}
};
draw2d.ExternalPalette = function(_5b29, divId) {
	this.html = document.getElementById(divId);
	this.workflow = _5b29;
	this.parts = new draw2d.ArrayList();
};
draw2d.ExternalPalette.prototype.type = "draw2d.ExternalPalette";
draw2d.ExternalPalette.prototype.getHTMLElement = function() {
	return this.html;
};
draw2d.ExternalPalette.prototype.addPalettePart = function(part) {
	if (!(part instanceof draw2d.AbstractPalettePart)) {
		throw "parameter is not instanceof [draw2d.AbstractPalettePart]";
	}
	this.parts.add(part);
	this.html.appendChild(part.getHTMLElement());
	part.setEnviroment(this.workflow, this);
};
