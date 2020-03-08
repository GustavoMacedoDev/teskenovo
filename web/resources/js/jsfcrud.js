function somar() {
    
    var teste = null;
    teste = document.getElementsByClassName('valor-total-lancamento');

    var total = 0;
    for (let i = 0; i < teste.length; i++) {
        let valor = parseFloat(teste[i].textContent);
        total = total + valor;
    }
    
    var totalPago = 0.0;

    totalPago = document.getElementById("datalistPago:0:valor-pago").innerHTML;
    
    console.log(totalPago);
    
    var totalMenosPago;
    
    totalMenosPago = total - totalPago;
            
    document.getElementById('resulta:resultado').innerHTML = totalMenosPago;
};

function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}

