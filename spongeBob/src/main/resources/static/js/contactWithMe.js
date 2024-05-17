// 获取表单和评论列表元素
const contactForm = document.getElementById('contactForm');
const commentForm = document.getElementById('commentForm');
const commentList = document.getElementById('commentList');

// 表单提交处理
contactForm.addEventListener('submit', function (event) {
    event.preventDefault();

    // 获取姓名、邮箱和留言内容
    const nameInput = document.getElementById('name');
    const emailInput = document.getElementById('email');
    const messageInput = document.getElementById('message');

    const name = nameInput.value;
    const email = emailInput.value;
    const message = messageInput.value;

    // 判断姓名、邮箱和留言内容长度是否小于100
    if (name.length > 100 || email.length > 100 || message.length > 100) {
        alert('姓名、邮箱或留言内容长度不能超过100个字符。');
        return;
    }

    // 创建成功提示消息
    const successMessage = document.createElement('div');
    successMessage.className = 'success-message';
    successMessage.textContent = `感谢您的留言，${name}！`;

    // 将成功提示消息添加到表单
    contactForm.appendChild(successMessage);

    // 清空表单输入
    nameInput.value = '';
    emailInput.value = '';
    messageInput.value = '';
});

// 评论表单提交处理
commentForm.addEventListener('submit', function (event) {
    event.preventDefault();

    // 获取姓名和评论内容
    const usernameInput = document.getElementById('username');
    const commentInput = document.getElementById('comment');

    const username = usernameInput.value;
    const comment = commentInput.value;

    // 判断姓名和评论内容长度是否小于100
    if (username.length > 100 || comment.length > 100) {
        alert('姓名或评论内容长度不能超过100个字符。');
        return;
    }

    // 获取当前时间戳作为评论的唯一标识符
    const timeFlag = Date.now().toString();

    // 创建评论元素
    const commentElement = document.createElement('div');
    commentElement.classList.add('comment');
    commentElement.dataset.commentId = timeFlag; // 存储评论的唯一标识符
    commentElement.innerHTML =
        `
    <h3>${username}</h3>
    <p>${comment}</p>
    <button class="delete-btn">删除</button>
    <button class="edit-btn">编辑</button>
    `;

    // 添加评论元素到评论列表
    commentList.appendChild(commentElement);

    // 清空评论表单输入
    usernameInput.value = '';
    commentInput.value = '';
});

// 监听评论列表的点击事件，委托给删除和编辑按钮
commentList.addEventListener('click', function (event) {
    const target = event.target;

    if (target.classList.contains('delete-btn')) {
        // 删除评论
        const commentElement = target.parentElement;
        commentElement.remove();
    } else if (target.classList.contains('edit-btn')) {
        // 编辑评论
        const commentElement = target.parentElement;
        const nameElement = commentElement.querySelector('h3');
        const commentTextElement = commentElement.querySelector('p');

        const name = nameElement.textContent;
        const comment = commentTextElement.textContent;

        // 将评论内容填充回评论表单
        const usernameInput = document.getElementById('username');
        const commentInput = document.getElementById('comment');
        usernameInput.value = name;
        commentInput.value = comment;

        // 存储当前编辑的评论的唯一标识符
        commentInput.dataset.commentId = commentElement.dataset.commentId;

        // 删除原来的评论
        commentElement.remove();
    }
});