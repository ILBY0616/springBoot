const navLinks = document.querySelectorAll('header ul li a');

navLinks.forEach(link => {
    link.addEventListener('click', function (e) {
        const href = this.getAttribute('href');

        // 检查链接是否为页面内链接
        if (href.startsWith('#')) {
            e.preventDefault();

            const targetId = href.substring(1);
            const targetElement = document.getElementById(targetId);

            if (targetElement) {
                targetElement.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        }
        // 外部链接保留默认行为
    });
});

// 左边的 div 元素从页面外移到当前位置
const leftDivs = document.querySelectorAll('.left');
leftDivs.forEach((div) => {
    setTimeout(() => {
        div.style.transform = 'translateX(0)';
    }, 0); // 0毫秒后开始移动
});

// 右边的 div 元素从页面外移到当前位置
const rightDivs = document.querySelectorAll('.right');
rightDivs.forEach((div) => {
    setTimeout(() => {
        div.style.transform = 'translateX(0)';
    }, 0); // 0毫秒后开始移动
});

