let loginForm = document.querySelector("#loginForm");
let registerForm = document.querySelector("#registerForm");
const nameRegex = /^[\u4E00-\u9FFF]+$/;
const emailRegex = /^[1-9][0-9]{4,10}@qq.com$/;
const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$/;
const confirmPasswordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$/;
loginForm.addEventListener("submit", function (event) {
    let loginEmail = document.querySelector("#loginEmail").value;
    let loginPassword = document.querySelector("#loginPassword").value;
    let loginEmailError = document.querySelector("#loginEmailError");
    let loginPasswordError = document.querySelector("#loginPasswordError");
    let valid = true;
    // 验证邮箱
    if (!emailRegex.test(loginEmail)) {
        loginEmailError.textContent = "*请输入有效的QQ邮箱";
        valid = false;
    } else {
        loginEmailError.textContent = "";
    }
    // 验证密码
    if (!passwordRegex.test(loginPassword)) {
        loginPasswordError.textContent = "*请输入包含数字以及大小写字母的十位有效密码";
        valid = false;
    } else {
        loginPasswordError.textContent = "";
    }
    // 拦截错误
    if (!valid) {
        event.preventDefault();
    }
});
registerForm.addEventListener("submit", function (event) {
    let registerName = document.querySelector("#registerName").value;
    let registerEmail = document.querySelector("#registerEmail").value;
    let registerPassword = document.querySelector("#registerPassword").value;
    let registerConfirmPassword = document.querySelector("#registerConfirmPassword").value;
    let registerAccord = document.querySelector("#registerAccord").checked;
    let registerNameError = document.querySelector("#registerNameError");
    let registerEmailError = document.querySelector("#registerEmailError");
    let registerPasswordError = document.querySelector("#registerPasswordError");
    let registerConfirmPasswordError = document.querySelector("#registerConfirmPasswordError");
    let valid = true;
    // 验证姓名
    if (!nameRegex.test(registerName)) {
        registerNameError.textContent = "*请输入有效的汉字姓名";
        valid = false;
    } else {
        registerNameError.textContent = "";
    }
    // 验证邮箱
    if (!emailRegex.test(registerEmail)) {
        registerEmailError.textContent = "*请输入有效的QQ邮箱";
        valid = false;
    } else {
        registerEmailError.textContent = "";
    }
    // 验证密码
    if (!passwordRegex.test(registerPassword)) {
        registerPasswordError.textContent = "*请输入包含数字以及大小写字母的十位有效密码";
        valid = false;
    } else {
        registerPasswordError.textContent = "";
    }
    // 验证确认密码
    if (!confirmPasswordRegex.test(registerConfirmPassword)) {
        registerConfirmPasswordError.textContent = "*请输入包含数字以及大小写字母的十位有效密码";
        valid = false;
    } else {
        registerConfirmPasswordError.textContent = "";
    }
    // 验证密码相同
    if (registerPassword !== registerConfirmPassword) {
        registerConfirmPasswordError.textContent = "*密码和确认密码不匹配";
        valid = false;
    }
    // 拦截错误
    if (!valid || !registerAccord) {
        event.preventDefault();
    }
});

