class Quadratic {
    #A;
    #B;
    #C;
    #delta;
    #x1;
    #x2;

    constructor(a, b, c) {
        this.#A = a;
        this.#B = b;
        this.#C = c;
        this.#delta = null;
        this.#x1 = null;
        this.#x2 = null;
    }

    setA(a) {
        this.#A = a;
    }

    setB(b) {
        this.#B = b;
    }

    setC(c) {
        this.#C = c;
    }

    getX1() {
        return this.#x1;
    }

    getX2() {
        return this.#x2;
    }

    getDelta() {
        return this.#delta;
    }

    calcDelta() {
        this.#delta = (this.#B * this.#B) - 4 * this.#A * this.#C;
        return this.#delta;
    }

    zeroPlaces() {
        let d = this.calcDelta();

        if (d > 0) {
            this.#x1 = ((this.#B * -1) - Math.sqrt(d)) / (2 * this.#A);
            this.#x2 = ((this.#B * -1) + Math.sqrt(d)) / (2 * this.#A);
        } else if (d == 0) {
            this.#x1 = (this.#B * -1) / (2 * this.#A);
            this.#x2 = null;
        } else {
            this.#x1 = null;
            this.#x2 = null;
        }
    }

    validate() {
        if (this.#A == null || isNaN(parseInt(this.#A)) || +this.#A == 0)
            return false;
        if (this.#B == null || isNaN(parseInt(this.#B)))
            return false;
        if (this.#C == null || isNaN(parseInt(this.#C)))
            return false;

        return true;
    }

    calculate() {
        if (this.validate()) {
            this.zeroPlaces();
        } else {
            alert("Not valid data provided");
        }
    }
}

function hook() {
    let element = document.getElementById("calculate");
    element.addEventListener("click", function (event) {
        let a = document.getElementById("coeff_a").value;
        let b = document.forms["data"].elements["cb"].value;
        let c = document.getElementById("coeff_c").value;

        let equation = new Quadratic(a, b, c);
        equation.calculate();

        if (equation.getDelta() == null)
            return false;

        let list = document.getElementById("list");
        let x1 = null;
        let x2 = null;

        if (list.hasChildNodes()) {
            while (list.firstChild) {
                list.removeChild(list.firstChild);
            }
        }

        let result = document.createTextNode("Result: ");

        if (equation.getDelta() > 0) {
            x1 = "x\u2081 = " + equation.getX1();
            x2 = "x\u2082 = " + equation.getX2();
        } else if (equation.getDelta() == 0) {
            x1 = "x = " + equation.getX1();
        } else {
            x1 = "No zero places, \u0394 < 0";
        }

        let ul = document.createElement("ul");
        let li = document.createElement("li");

        let liText = document.createTextNode(x1);
        li.appendChild(liText);
        ul.appendChild(li);

        list.appendChild(result);
        list.appendChild(ul);

        if (x2) {
            let li2 = document.createElement("li");
            let liText2 = document.createTextNode(x2);

            li2.appendChild(liText2);
            ul.appendChild(li2);
        }
    });
}
