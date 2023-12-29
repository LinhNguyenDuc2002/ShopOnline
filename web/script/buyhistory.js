
var a = 0;
function Show(type, element, type2) {
    a += 1;
    let sum = a;
    const displays = document.querySelectorAll("." + type + "");
    const typeClass = document.querySelector("." + type2 + "");
    if (sum % 2 != 0) {
        if (element.classList.contains("fa-chevron-down")) {
            element.classList.remove("fa-chevron-down");
            element.classList.add("fa-chevron-up");
        }
        for (let i = 0; i < displays.length; i++) {
            let node = displays[i];
            // Thực hiện công việc với node tại đây
            node.style.display = "block";
            node.style.transition = "1.5s ease-in-out";

            node.style.transform = "translateX(0)";

        }

        console.log(displays);
    } else if (sum % 2 == 0) {
        if (element.classList.contains("fa-chevron-up")) {
            element.classList.remove("fa-chevron-up");
            element.classList.add("fa-chevron-down");
        }
        //                displays.style.display = "none";
        //                console.log("Đã đóng");

        for (let i = 0; i < displays.length; i++) {
            let node = displays[i];
            close(node);
            // Thực hiện công việc với node tại đây
            node.style.transform = "translateX(-200%)";

        }

        typeClass.style.height = "auto";


    }
    console.log(sum);
    console.log(displays);


}

//        function Show(type){
//            const displays = document.querySelector("."+type+"");
//            displays.style.display = "block";
//                console.log(displays);
//        }
//        
function close(types) {
    //                                const displays1s = document.querySelector("." + types + "");
    types.style.display = "none";
    console.log(types);
}

