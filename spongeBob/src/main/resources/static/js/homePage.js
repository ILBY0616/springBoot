// 获取所有卡片元素
const cards = document.querySelectorAll('.card');
cards.forEach(card => {
    card.style.transform = 'translateX(-100%)'; // 初始位置左侧屏幕外
});

// 记录上一次滚动的位置
let lastScrollTop = 0;

// 监听滚动事件
window.addEventListener('scroll', () => {
    const currentScroll = document.documentElement.scrollTop;

    // 向下滚动
    if (currentScroll > lastScrollTop) {
        cards.forEach(card => {
            const cardPosition = card.getBoundingClientRect().top;

            // 如果卡片进入视口，将 left 设置为 0，使其从左侧滑动到中间
            if (cardPosition < window.innerHeight / 1.5) {
                card.style.transform = 'translateX(0)';
            }
        });
    }
    // 向上滚动
    else {
        cards.forEach(card => {
            card.style.transform = 'translateX(-100%)'; // 回到左侧屏幕外
        });
    }

    lastScrollTop = currentScroll <= 0 ? 0 : currentScroll;
});

function updateTime() {
    let currentTime = new Date();
    let hours = currentTime.getHours();
    let minutes = currentTime.getMinutes();
    let seconds = currentTime.getSeconds();

    // 在一位数前面添加零以保持时间格式一致
    hours = (hours < 10 ? "0" : "") + hours;
    minutes = (minutes < 10 ? "0" : "") + minutes;
    seconds = (seconds < 10 ? "0" : "") + seconds;

    document.getElementsByTagName("h1")[0].innerHTML = hours + ":" + minutes + ":" + seconds;
}

// 初始加载时间
updateTime();

// 每秒钟更新一次时间
setInterval(updateTime, 1000);
