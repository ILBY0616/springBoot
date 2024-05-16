let num = 0;
setInterval(
    () => {
        if (num === 3) {
            num = 0
        }
        num++;
        document.querySelector("img").src = `/resource/image/groupPhoto0${num}.jpg`;
    }, 1500
);

let topButton = document.getElementById('topButton');

// 当页面滚动时，显示或隐藏回到顶部按钮
window.onscroll = function () {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        topButton.style.display = 'block';
    } else {
        topButton.style.display = 'none';
    }
};

// 回到顶部函数
function toTop() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}