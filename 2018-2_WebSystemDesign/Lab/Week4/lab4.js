
var ac;

function Account(nm, pw){
    var name = nm;
    var password = pw;
    var balance = parseInt("0");

    return {
        // 입금
            deposit(money) {
            if(money === "" || parseInt(money) < parseInt("0"))
                return parseInt("-1"); //error
            else
                return balance += parseInt(money); // right
            },
        // 출금
            withdraw(in_pw, money) {
                if(password !== in_pw)
                    return parseInt("-1"); // password error
                else if(money === "" || parseInt(money) < parseInt("0") || parseInt(money) > balance)
                    return parseInt("-2"); // money error
                else
                    return balance -= parseInt(money); // right

            },
        // 잔액 확인
            getBalance(in_pw) {
                if(password === in_pw)
                    return balance;
                else
                    return parseInt("-1"); //error

            },
    }
}

document.querySelector("#MNA").addEventListener("click", function onMakeAccountEvent(event) { //계좌생성

    var name = document.querySelector("#name").value;
    var password = document.querySelector("#PW1").value;

    ac = new Account(name, password);

    document.querySelector("#new-account-div").style.display = "none";
    document.querySelector("#main-menu-div").style.display = "block";
});

document.querySelector("#DP").addEventListener("click", function onDepositEvent(event) { //입금(Deposit)

    var dp_balance = ac.deposit(document.querySelector("#money").value);

    if(dp_balance < 0){
        document.querySelector("#balance-value1").innerHTML = "MONEY RANGE IS INCORRECT";
        document.querySelector("#balance-value2").innerHTML = "";
        document.querySelector("#balance-value3").innerHTML = "";
    }
    else {
        document.querySelector("#balance-value1").innerHTML = "잔고 : ";
        document.querySelector("#balance-value2").innerHTML = dp_balance;
        document.querySelector("#balance-value3").innerHTML = " 원";
    }

    if(document.querySelector("#PW2").value === "" && document.querySelector("#money").value === ""){
        document.querySelector("#balance-value1").innerHTML = "";
        document.querySelector("#balance-value2").innerHTML = "";
        document.querySelector("#balance-value3").innerHTML = "";
    }

    document.querySelector("#PW2").value = "";
    document.querySelector("#money").value = "";
});

document.querySelector("#WD").addEventListener("click", function onWithdrawEvent(event) { //출금(Withdraw)

    var wd_balance = ac.withdraw(document.querySelector("#PW2").value, document.querySelector("#money").value);

    if (wd_balance < 0) {
        if(wd_balance === parseInt("-1")) { //password error
            document.querySelector("#balance-value1").innerHTML = "PASSWORD IS INCORRECT";
            document.querySelector("#balance-value2").innerHTML = "";
            document.querySelector("#balance-value3").innerHTML = "";
        }
        else if(wd_balance === parseInt("-2")) { //Money error
            document.querySelector("#balance-value1").innerHTML = "MONEY RANGE IS INCORRECT";
            document.querySelector("#balance-value2").innerHTML = "";
            document.querySelector("#balance-value3").innerHTML = "";
        }
    }
    else {
        document.querySelector("#balance-value1").innerHTML = "잔고 : ";
        document.querySelector("#balance-value2").innerHTML = wd_balance;
        document.querySelector("#balance-value3").innerHTML = " 원";
    }

    if(document.querySelector("#PW2").value === "" && document.querySelector("#money").value === ""){
        document.querySelector("#balance-value1").innerHTML = "";
        document.querySelector("#balance-value2").innerHTML = "";
        document.querySelector("#balance-value3").innerHTML = "";
    }

    document.querySelector("#PW2").value= "";
    document.querySelector("#money").value = "";
});

document.querySelector("#GB").addEventListener("click", function onGetBalanceEvent(event) { //잔고확인(GetBalance)

    var get_balance = ac.getBalance(document.querySelector("#PW2").value);

    if(get_balance < 0) {
        document.querySelector("#balance-value1").innerHTML = "PASSWORD IS INCORRECT";
        document.querySelector("#balance-value2").innerHTML = "";
        document.querySelector("#balance-value3").innerHTML = "";
    }
    else {
        document.querySelector("#balance-value1").innerHTML = "잔고 : ";
        document.querySelector("#balance-value2").innerHTML = get_balance;
        document.querySelector("#balance-value3").innerHTML = " 원";
    }

    if(document.querySelector("#PW2").value === "" && document.querySelector("#money").value === ""){
        document.querySelector("#balance-value1").innerHTML = "";
        document.querySelector("#balance-value2").innerHTML = "";
        document.querySelector("#balance-value3").innerHTML = "";
    }

    document.querySelector("#PW2").value = "";
    document.querySelector("#money").value = "";
});