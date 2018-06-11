Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});

function getData(url, id) {
    var to = document.getElementById("to");
    var toString = to.options[to.selectedIndex].text;

    var from = document.getElementById("from");
    var fromString = from.options[from.selectedIndex].text;

    disableOptions(to, from);

    url += '?from='+fromString+ "&to="+toString;

    if (document.getElementById('datePicker'))
        url += "&date="+document.getElementById('datePicker').value;
    //alert(url);
    $.get(url, function(data) {
        $('#'+id).html(data);
    });
}

function disableOptions(to, from) {
    var option;
    for (option of to.options)
        if (option === to.options[from.selectedIndex])
            option.disabled = "disabled";
        else option.disabled = "";

    for (option of from.options)
        if (option === from.options[to.selectedIndex])
            option.disabled = "disabled";
        else option.disabled = "";
}
