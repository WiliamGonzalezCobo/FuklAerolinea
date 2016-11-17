function validarNumero(numeroT, franquiciaValue) {
	if (franquiciaValue == "1") { // Visa
		var re = /^4\d{3}-?\d{4}-?\d{4}-?\d{4}$/;
		var msg = erroresFranquicias.visa;
	}

	if (franquiciaValue == "2") { // MasterCard
		var re = /^(2|5)[1-5]\d{2}-?\d{4}-?\d{4}-?\d{4}$/;
		var msg = erroresFranquicias.master;
	}

	if (franquiciaValue == "3") { // Dinners
		var re = /^3[0,6,8]\d{12}$/;
		var msg = erroresFranquicias.diners;
	}

	if (franquiciaValue == "4") { // AmericanExpress
		var re = /^3[4,7]\d{13}$/;
		var msg = erroresFranquicias.american;
	}

	if (franquiciaValue == null || franquiciaValue == "") {
		return true;
	}

	if (value.length < 14 || value.length > 16) {
		return false;
	}

	numeroT = numeroT == null ? "" : numeroT;

	if (!re.test(numeroT)) {
		return false;
	} else {
		return validacionLongitudTarjetaCredito(numeroT);
	}
}

function validacionLongitudTarjetaCredito(value) {
	var checksum = 0;
	for (var i = (2 - (value.length % 2)); i <= value.length; i += 2) {
		checksum += parseInt(value.charAt(i - 1));
	}

	for (var i = (value.length % 2) + 1; i < value.length; i += 2) {
		var digit = parseInt(value.charAt(i - 1)) * 2;
		if (digit < 10) {
			checksum += digit;
		} else {
			checksum += (digit - 9);
		}
	}

	if ((checksum % 10) == 0) {
		return true;
	} else {
		return false;
	}

	return;
}

function payValidate() {	
	var fran = $("#cboFranquicia").val();
	var num = $("#txtNumTarjeta").val();
	var exp = $("#cboMes").val() + "/" + $("#cboAno").val();
	var cod = $("#txtCVV").val();

	if (validarNumero(num, fran)) {
		$.ajax({
			url : "http://localhost:3000/pay/",
			data : JSON.stringify(clientTest),
			type : "POST",
			contentType : 'text/plain',
			dataType : "json",
			success : function(data, textStatus, xhr) {
				return true;
			},
			error : function(xhr, textStatus, errorThrown) {
				return false;
			}
		});
	}
}
