function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}

function soma() {
    let tdsValores = document.querySelectorAll('.valor');
    console.log(tdsValores);

    let total = 0;
    for (let i = 0; i < tdsValores.length; i++) {
        let valor = parseFloat(tdsValores[i].textContent);
        total = total + valor;
    }
    console.log(total);
    document.getElementById('resultado').innerHTML = total;
}
;