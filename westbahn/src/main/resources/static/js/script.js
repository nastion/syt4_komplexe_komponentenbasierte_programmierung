Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});

function getData() {
    var to = document.getElementById("to");
    var toString = to.options[to.selectedIndex].text;

    var from = document.getElementById("from");
    var fromString = from.options[from.selectedIndex].text;

    for (var option of to.options)
        if (option === to.options[from.selectedIndex])
            option.disabled = "disabled";
        else option.disabled = "";

    for (var option of from.options)
        if (option === from.options[to.selectedIndex])
            option.disabled = "disabled";
        else option.disabled = "";

    var url = '/update?from='+fromString+ "&to="+toString;

    if (document.getElementById('datePicker'))
        url += "&date="+document.getElementById('datePicker').value;
    $.get(url, function(data) {
        $('#data').html(data);
    });
}
