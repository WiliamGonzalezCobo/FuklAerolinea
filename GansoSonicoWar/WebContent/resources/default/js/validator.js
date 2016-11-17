$ = jQuery;
$('#registrationForm').bootstrapValidator({
 
	 feedbackIcons: {
 
		 valid: 'glyphicon glyphicon-ok',
 
		 invalid: 'glyphicon glyphicon-remove',
 
		 validating: 'glyphicon glyphicon-refresh'
 
	 },
	 
	 fields: {
 
		 'registrationForm\\:nombre': {
 
			 validators: {
 
				 notEmpty: {
 
					 message: 'El nombre es requerido'
 
				 }
 
			 }
 
		 },
 
		 'registrationForm\\:apellido': {
 
			 validators: {
 
				 notEmpty: {
 
					 message: 'El apellido es requerido'
 
				 }
 
			 }
 
		 }
	 }
});