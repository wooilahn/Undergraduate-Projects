document.querySelector("#AT").addEventListener("click", function onTextEvent(event) {

    var text = document.createElement("p");
    var parentDiv = document.querySelector("#parentDiv");

    text.className = "textClass";

    text.innerText = document.getElementById("input").value;
    parentDiv.appendChild(text);
})

document.querySelector("#FL").addEventListener("change", function onChangeEvent(event) {
    var fr = new FileReader();
    var img = document.createElement("img");
    var parentDiv = document.querySelector("#parentDiv");

    img.className = "imgClass";

    fr.onload = function() {
        img.src = fr.result;
        img.classList.add("margin-bottom");
        parentDiv.appendChild(img);
    }
    fr.readAsDataURL(event.target.files[0]);
})

document.querySelector("#CA").addEventListener("click", function onClearEvent(event) {

    document.querySelector("#FL").value = "";
    document.querySelector("#input").value= "";

    var parentDiv = document.querySelector("#parentDiv");
    parentDiv.innerHTML = "";
})

