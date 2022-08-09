$(document).ready(function() {
    var $add = $("#add");
    var $items = $("#items");
    var $item = $("#item");

    $add.click(function() {
        console.log("HERE")
        addItem($item.val());
        $item.val("");
    });

    var addItem = function(item) {
        var li = $("<li></li>");
        li.html(item);
        $items.append(li);
    };
});