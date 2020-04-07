ateExpression');
		var clazz = $(this).attr('class');
		if (expression != null && expression != "") {
			listener.serviceType = 'expression';
			listener.serviceExpression = expression;
		} else if (clazz != null && clazz != "") {
			listener.serviceType = 'javaClass';
			listener.serviceClass = clazz;
		}
		var fields = $(this).find('activiti\\:field');
		fields.each(function(i) {
			var field = eval("new " + fieldType + "()");
			field.name = $(this).attr('name');
			var string = $(this).find('activiti\\:string').text();
			var expression = $(this).find('activiti\\:expression').text();
			if (string != null && string != "") {
				field.type = 'string';
				field.value = string;
			} else if (expression != null && expression != "") {
				field.type = 'expression';
				field.value = expression;
			}
			listener.setField(field);
		});
		parsedListeners.add(listener);
	});
	return parsedListeners;
}
function parseForms(forms, formType) {
	var parsedForms = new draw2d.ArrayList();
	forms.each(function(i) {
		var form = eval("new " + formType + "()");
		form.id = $(this).attr('id');
		var name = $(this).attr('name');
		form.name = name;
		var type = $(this).attr('type');
		form.type = type;
		var value = $(this).attr('value');
		form.value = value;
		var exp = $(this).attr('exp');
		form.exp = exp;
		var remark = $(this).attr('remark');
		form.remark = remark;
		parsedForms.add(form);
	});
	return parsedForms;
}