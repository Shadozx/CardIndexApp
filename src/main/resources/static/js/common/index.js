// let catalogsNew = 0,
//   cardsNew = 0

// const buttons = document.getElementsByClassName('button')
// for (let button of button) {
//   button.onclick = addCatalog
// }
// window.onload = function () {
// document.addEventListener('DOMContentLoaded', () => {
// document.getElementById('submCard').onclick = () => {
//   addCard()
//   return true
// }
// document.getElementById('submCatalog').onclick = () => {
//   addCatalog()
//   return true
// }
// // }
// // }
// function addCatalog() {
//   /**
//    * <div class="catalog">
//               <div class="catalog-name"><a href="id.html">Title</a></div>
//             </div>
//    */
//   console.log('here')
//   const catalogname = document.querySelector("#catalogId [name='catalog-name'")
//   // const description = document.querySelector("#cardId [name='description'")
//   const catalog = document.createElement('div')
//
//   catalog.classList.add('catalog')
//
//   const catalogName = document.createElement('div')
//
//   catalogName.classList.add('catalog-name')
//
//   const link = document.createElement('a')
//   //   link.classList.add()
//
//   link.href = 'id.html'
//   link.innerHTML = catalogname.value
//
//   catalogName.appendChild(link)
//
//   catalog.appendChild(catalogName)
//
//   document.getElementById('id').appendChild(catalog)
//   console.log('Finish')
//   resetCatalog()
//
//   return true
// }
//
// function addCard() {
//   console.log('Here!')
//   const cardname = document.querySelector("#cardId [name='card-name'")
//   const description = document.querySelector("#cardId [name='description'")
//   const card = document.createElement('div')
//
//   card.classList.add('card')
//
//   const cardName = document.createElement('div')
//   cardName.classList.add('card-name')
//   const link = document.createElement('a')
//   //   link.classList.add()
//   link.innerHTML = cardname.value
//   link.href = 'card.html'
//   cardName.appendChild(link)
//   card.appendChild(cardName)
//   document.getElementById('id').appendChild(card)
//   resetCard()
//   return true
// }
//
// function resetCatalog() {
//   const form = document.forms['catalogForm']
//   form.reset()
// }
// function resetCard() {
//   const form = document.forms['cardForm']
//   form.reset()
// }
// var $ = jQuery;


document.addEventListener('DOMContentLoaded', () => {

    addDelete()
    addAutoResingTextarea()
    // addCreateCatalogsCards()
    showHiddenText()
})


async function addDelete() {
    const forDelete = document.getElementsByClassName("delete")

    for(let d of forDelete) {

        d.addEventListener("click", async function (e) {
            e.preventDefault()
            const response = await fetch(d.href, {method: "delete"})

            if(response.ok) {
                window.location.replace('/');
            }
        })
    }
}

function addAutoResingTextarea() {

        const tx = document.getElementsByTagName('textarea')
        for (let i = 0; i < tx.length; i++) {
            tx[i].setAttribute(
                'style',
                'height:' + tx[i].scrollHeight + 'px;overflow-y:hidden;'
            )
            tx[i].addEventListener('input', OnInput, false)
        }

        function OnInput() {
            this.style.height = 0
            this.style.height = this.scrollHeight + 'px'
        }

}


function addCreateCatalogsCards() {
    const catalogsCreate = document.getElementsByClassName('catalog-create')
    const cardsCreate = document.getElementsByClassName('card-create')

    for(let c of catalogsCreate) {
        c.addEventListener('click', function (e) {
            e.preventDefault()
            document.getElementById('newCatalog').style.display = 'block';
        })
    }

    for(let c of cardsCreate) {
        c.addEventListener('click', function (e) {
            e.preventDefault()
            document.getElementById('newCard').style.display = 'block';
        })
    }
}

function showHiddenText() {
    const ps = document.getElementsByTagName('p')

    for (let p of ps) {
        p.addEventListener('click', function (e) {
            e.preventDefault()
            console.log(p.classList)

            if (p.classList.contains('active')) p.classList.remove('active')
            else p.classList.add('active')
        })
    }
}