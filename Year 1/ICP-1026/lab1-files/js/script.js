(function() {

    var touches = [];
    var canvas, ctx;

    var init = function() {
        canvas = $("#vis-canvas")[0];
        ctx = canvas.getContext("2d");

        $(canvas).click(click);
        setInterval(play, 20);
    };

    var click = function(event) {
        touches.push({
            x: event.offsetX,
            y: event.offsetY,
            r: 1,
            h: Math.round(Math.random() * 360)
        });
    };

    var eachTouch = function(fn) {
        for(var i = 0; i < touches.length; i++) {
            fn(touches[i], i);
        }
    };

    var update = function() {
        eachTouch(function(touch, i) {
            if(touch.r < 100) {
                touch.r++;
            } else {
                touches.splice(i, 1);
            }
        });
    };

    var render = function() {
        ctx.fillStyle="#eee";
        ctx.fillRect(0, 0, 600, 400);
        eachTouch(function(touch) {
            ctx.save();

            ctx.translate(touch.x, touch.y);
            ctx.fillStyle = "hsla("+touch.h+", 50%, 70%, 0.8)"; 
            ctx.beginPath();
            ctx.arc(0, 0, touch.r, 0, Math.PI*2, false);
            ctx.fill();
            ctx.closePath();

            ctx.restore();
        });
    };

    var play = function() {
        update();
        render();
    };

    $(document).ready(init);
})();