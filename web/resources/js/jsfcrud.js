function somar() {
    
    console.log('opa');
    
    var teste = null;
    teste = document.getElementsByClassName('valor');

    console.log(teste);
    console.log('eae');

    var total = 0;
    for (let i = 0; i < teste.length; i++) {
        let valor = parseFloat(teste[i].textContent);
        total = total + valor;
    }
    
    console.log(total);
    
    document.getElementById('resulta:resultado').innerHTML = total;
};

function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}

