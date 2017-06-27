function showMenu(){
    var menuStyle = document.getElementById("menu").style;
    menuStyle.marginLeft = "0";
    var shdwMenuStyle = document.getElementById("shdwMenu").style;
    shdwMenuStyle.display = "flex";
    var fnStyle = document.getElementById("fn").style;
    fnStyle.visibility = "hidden";
}
function hideMenu(){
    if(document.body.clientWidth <= 900){
        document.getElementById("menu").style.marginLeft = "-50%";
    }else if(document.body.clientWidth <= 1150){
        document.getElementById("menu").style.marginLeft = "-30%";
    }else if(document.body.clientWidth <= 1360){
        document.getElementById("menu").style.marginLeft = "-24%";
    }else if(document.body.clientWidth > 1360){
        document.getElementById("menu").style.marginLeft = "-24%";
    }
    document.getElementById("shdwMenu").style.display = "none";
    var fnStyle = document.getElementById("fn").style;
    fnStyle.visibility = "visible";
}

window.addEventListener("scroll",function(){
    var cLeft = document.getElementById("cLeft");
    var cLeftH = cLeft.clientHeight - window.innerHeight;
    var cRight = document.getElementById("cRight");
    var cRightH = cRight.clientHeight - window.innerHeight;
    if ( window.pageYOffset > cLeftH){
        cLeft.style.position = "fixed";
    }else if ( window.pageYOffset < cLeftH){
        cLeft.style.position = "absolute";
    }
    if ( window.pageYOffset > cRightH){
        cRight.style.position = "fixed";
    }else if ( window.pageYOffset < cRightH){
        cRight.style.position = "absolute";
    }
});
function toggleIndx(){
    var stats = document.getElementById("toggleIndx");
    var indxUl = document.getElementById("indx").children[1];
    if(stats.innerHTML == "ocultar"){
        stats.innerHTML = "mostrar";
        indxUl.style.display = "none";
    }else{
        stats.innerHTML = "ocultar";
        indxUl.style.display = "block";
    }
}
function homeSortAppear(){
    var sortOption = document.getElementById("hSort");
    if (sortOption.style.display != "block") {
        sortOption.style.display = "block";
    }else{
        sortOption.style.display = "none";
    }
}
function homeSort(o){
    var options = document.getElementById("hSort").children;
    if(o == 0){
        options[0].children[0].style.visibility = "visible";
        options[1].children[0].style.visibility = "hidden";
        document.getElementById("hSort").style.display = "none";
    }else if(o == 1){
        options[1].children[0].style.visibility = "visible";
        options[0].children[0].style.visibility = "hidden";
        document.getElementById("hSort").style.display = "none";
    }
    
}