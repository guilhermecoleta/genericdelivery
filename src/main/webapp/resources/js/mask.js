$(document).ready(function() {
	applyMasks();
});

function applyMasks(){
	applyMask("(99) 9999-9999", "phone");
	applyMask("(99) 99999-9999", "cel");
	applyMask("999.999.999-99", "cpf");
	applyMask("99.999.999-*", "rg");
	applyMask("99999-999", "cep");
	moneyMask("currency");
}


function applyMask(mask, cssClass) {
	$('.' + cssClass).on("input paste", function() {
		$(this).mask(mask, {
			placeholder : '_'
		});
	});
}

function moneyMask(cssClass) {
	$('.' + cssClass).on("input paste", function() {
		item = $(this);
		
		item.priceFormat({
			prefix : '',
			centsSeparator : ',',
			thousandsSeparator : '.',
			limit : this.maxLenght,
			centsLimit : 2
		});
	});
}

function isNumeric(myfield, e, dec)
{
    var key;
    

    if (window.event)
    {
        key = window.event.keyCode;
    }
    else if (e)
    {
        key = e.which;
    }
    else
    {
        return true;
    }
    if(key != 46 && key != 45 && key > 31 && (key < 48 || key > 57))
    {
        return false;
    }
    else
    {
        return true;
    }
}

function removeNonNumeric(myfield)
{
    var re = /[^0-9\.\-]/g;
    if(re.test(myfield.value))
    {
        myfield.value = myfield.value.replace(re, '');
        myfield.value = myfield.value.replace(/\./, '');
    }
}