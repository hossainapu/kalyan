$(function() {
    var toggleFunction;
    $('.toggle-handle').click(toggleFunction = function() {
        var area = $('#' + $(this).attr('data-area'));
        if(area.hasClass('expanded')) {
            area.removeClass('expanded');
        } else {
            area.addClass('expanded');
        }
        $(this).blur();
        return false;
    });
});