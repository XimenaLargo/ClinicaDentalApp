window.addEventListener("load", function (){

    const listaDeOdontologos = document.getElementById("listar");
    const btnRegistro = document.getElementById("registroNuevo");
    const registro = document.getElementById('odontologo-registro');
    const insertRow = document.getElementById("body-table");
    const formRegistro = document.getElementById('registro')
    const inputNombre = document.getElementById('OdontologoNombre');
    const inputApellido = document.getElementById('OdontologoApellido');
    const inputMatricula = document.getElementById('odontologoMatricula');

    const id_odontologo = document.getElementById('odontologo_id')
    const name_odontologo = document.getElementById('nombre')
    const lastName_odontologo = document.getElementById('apellido')
    const matricula_odontologo = document.getElementById('matricula')

    const update_form = document.getElementById('update_odontologo_form')

    const URL_BASE= "http://localhost:8080"

   
    function listarOdontologos(){
      const url = `${URL_BASE}/odontologos`;
    
      fetch(url)
        .then(response => {
          return response.json()
        })
        .then(data =>{
          console.log("Respuesta del getTodos");
        console.log(data);
        renderizarOdontologos(data)

      }) 
    }

    function renderizarOdontologos(arr){
      insertRow.innerHTML='';

      for(let i= 0 ; i< arr.length; i++){

        let get_More_Info_Btn = `<button id=btn_id_${arr[i].id} type="button" class="btn btn-dark btn_id" data-bs-toggle="modal" data-bs-target="#exampleModal">${arr[i].id}</button>`
        let tr_id = 'tr_'+arr[i].id
        let odontologoRow = 	`
          <tr id=${tr_id}> 
            <td>${get_More_Info_Btn} </td>
            <td class="td_first_name">${arr[i].nombre.toUpperCase()}</td>
            <td class="td_last_name">${arr[i].apellido.toUpperCase()}</td>
            <td class="td_matricula">${arr[i].matricula}</td>
            <td class="d-flex-justify-content-center"><button id="${arr[i].id}" type="submit" class="delete btn-close ps-5"></button></td>
            </tr>
          </tr>`;

      insertRow.innerHTML+= odontologoRow;
      } 
    }

    listaDeOdontologos.addEventListener('click', e =>{
    
      e.preventDefault();

      listarOdontologos();
    })

    function registrarOdontologo(){
      const payload={
        nombre:inputNombre.value,
        apellido: inputApellido.value,
        matricula: inputMatricula.value
    }

      const settings={
        method: 'POST',
        body: JSON.stringify(payload),
        headers:{
            'Content-type':'application/json'
        }
    }
    const url = `${URL_BASE}/odontologos`
  
    fetch(url,settings)
    .then(response =>{
        return response.json()
    })
    .then(data =>{

      console.log("Respuesta del post");
        console.log(data)

    }).catch(err => {

        console.log("Promesa rechazada:" + err);
        
    })
    }

    btnRegistro.addEventListener('click', e=>{

      e.preventDefault()

      registro.classList.remove("d-none")
    })

    formRegistro.addEventListener('submit', e=>{

      e.preventDefault();

      registrarOdontologo();

      formRegistro.reset();

    })

    document.addEventListener('click', e => {
      if (e.target.classList.contains("delete")) {
        borrarOdontologo(e.target.id);
        e.target.parentElement.parentElement.remove();
      }
    });

    function borrarOdontologo(id) {

      const url = `${URL_BASE}/odontologos/${id}`
      
      const settings ={
        method: 'DELETE',
        headers: {
          'Content-type':'application/json'
        }
      }
      fetch(url, settings)
      .then(response => {
        console.log("Respuesta del delete");
        console.log(response);
      })
      .then(data =>{
      })
      .catch(err => {

        console.log("Promesa rechazada:" + err);
        
    })
    };

    function buscarPorId(){
      document.addEventListener('click', e =>{

        if(e.target.classList.contains('btn_id')){
          
          let id_of_button = (e.target.id);
          let odontologoId = id_of_button.split("_")[2];
    
          const url = `${URL_BASE}/odontologos/${odontologoId}`;
    
          fetch(url)
            .then(response => {
              return response.json()
            })
            .then(data =>{
              console.log("Respuesta del get por id");
            console.log(data);
            id_odontologo.value = data.id;
            name_odontologo.value = data.nombre;
            lastName_odontologo.value = data.apellido;
            matricula_odontologo.value = data.matricula;
          })
          .catch(err => {

            console.log("Promesa rechazada:" + err);
            
        })
        }
      })
    }
  buscarPorId();

    function actualizarOdontologo(){

      update_form.addEventListener('submit', e =>{

        e.preventDefault();

          let payload = {
            id: id_odontologo.value,
            nombre : name_odontologo.value,
            apellido : lastName_odontologo.value,
            matricula: matricula_odontologo.value,
        }
        const settings={
          method: 'PUT',
          body: JSON.stringify(payload),
            headers:{
              'Content-type':'application/json'
            }
        }
        const url = `${URL_BASE}/odontologos`
      
        fetch(url,settings)
          .then(response =>{
            return response.json()
          })
          .then(data =>{
            listarOdontologos();
          console.log("Respuesta del put");
          console.log(data)

          }).catch(err => {
           console.log("Promesa rechazada:" + err);
          })
      })
    }
    actualizarOdontologo();
})