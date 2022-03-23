function comboChange(){
    var str = document.getElementById("comboReg").value;
    
    document.location.href="App?serviceToShow="+str;
}