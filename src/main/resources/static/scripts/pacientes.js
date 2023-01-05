window.addEventListener("load", function (){

    const pacientesList = document.getElementById("listarPacientes");
    const btnRegistro = document.getElementById("registroNuevo");
    const registro = document.getElementById('paciente-registro');
    const insertRow = document.getElementById("body-table");
    const formRegistro = document.getElementById('registro')
    const inputNombre = document.getElementById('pacienteNombre');
    const inputApellido = document.getElementById('pacienteApellido');
    const inputDni = document.getElementById('pacienteDni');
    const inputFechaAlta = document.getElementById('pacienteAlta')

    const inputDomicilioCalle = document.getElementById('domicilioCalle')
    const inputDomicilioNumero = document.getElementById('domicilioNumero');
    const inputDomicilioLocalidad = document.getElementById('domicilioLocalidad');
    const inputDomicilioProvincia = document.getElementById('domicilioProvincia');
    

    const id_paciente = document.getElementById('paciente_id')
    const nombre_paciente = document.getElementById('nombre')
    const apellido_paciente = document.getElementById('apellido')
    const dni_paciente = document.getElementById('dni')
    const fechaDeAlta = document.getElementById('fechaDeAlta')
    const domicilioCalle = document.getElementById('calle')
    const domicilioNumero = document.getElementById('numero');
    const domicilioLocalidad = document.getElementById('localidad');
    const domicilioProvincia = document.getElementById('provincia');


    const update_form = document.getElementById('update_paciente_form')

    const URL_BASE= "http://localhost:8080"

   
    function listarPacientes(){
      const url = `${URL_BASE}/pacientes`;
    
      fetch(url)
        .then(response => {
          return response.json()
        })
        .then(data =>{
        console.log(data);
        renderizarPacientes(data)

      }) 
    }

    function renderizarPacientes(arr){
      insertRow.innerHTML='';

      for(let i= 0 ; i< arr.length; i++){

        let get_More_Info_Btn = `<button id=btn_id_${arr[i].id} type="button" class="btn btn-dark btn_id" data-bs-toggle="modal" data-bs-target="#exampleModal">${arr[i].id}</button>`
        let tr_id = 'tr_'+ arr[i].id
        let pacienteRow = 	`
          <tr id=${tr_id}> 
            <td>${get_More_Info_Btn} </td>
            <td class="td_first_name">${arr[i].nombre.toUpperCase()}</td>
            <td class="td_last_name">${arr[i].apellido.toUpperCase()}</td>
            <td class="td_dni">${arr[i].dni}</td>
            <td class="td_fechaDeAlta">${arr[i].fechaDeAlta}</td>
            <td class="td_domicilio">Calle ${arr[i].domicilio.calle} # ${arr[i].domicilio.numero} ${arr[i].domicilio.localidad} - ${arr[i].domicilio.provincia} </td>

            <td class="d-flex-justify-content-center"><button id="${arr[i].id}" type="submit" class="delete btn-close ps-5"></button></td>
            </tr>
          </tr>`;

      insertRow.innerHTML+= pacienteRow;
      } 
    }

    pacientesList.addEventListener('click', e =>{
    
      e.preventDefault();

      listarPacientes();
    })

    function registrarPaciente(){

      const payload={
        nombre:inputNombre.value,
        apellido: inputApellido.value,
        dni: inputDni.value,
        domicilio:{
          calle:inputDomicilioCalle.value,
          numero:inputDomicilioNumero.value,
          localidad:inputDomicilioLocalidad.value,
          provincia:inputDomicilioProvincia.value
        },
        fechaDeAlta: inputFechaAlta.value
    }

      const settings={
        method: 'POST',
        body: JSON.stringify(payload),
        headers:{
            'Content-type':'application/json'
        }
    }
    const url = `${URL_BASE}/pacientes`
  
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

      registrarPaciente();

      formRegistro.reset();

    })

    document.addEventListener('click', e => {
      if (e.target.classList.contains("delete")) {
        borrarPaciente(e.target.id);
        e.target.parentElement.parentElement.remove();
      }
    });

    function borrarPaciente(id) {

      const url = `${URL_BASE}/pacientes/${id}`
      
      const settings ={
        method: 'DELETE',
        headers: {
          'Content-type':'application/json'
        }
      }
      fetch(url, settings)
      .then(response => {
        console.log(response);
      })
      .then(data =>{
      })
  
    };

    function buscarPorId(){
      document.addEventListener('click', e =>{

        if(e.target.classList.contains('btn_id')){
          
          let id_of_button = (e.target.id);
          let pacienteId = id_of_button.split("_")[2];
    
          const url = `${URL_BASE}/pacientes/${pacienteId}`;
    
          fetch(url)
            .then(response => {
              return response.json()
            })
            .then(data =>{
            console.log(data);
            id_paciente.value = data.id;
            nombre_paciente.value = data.nombre;
            apellido_paciente.value = data.apellido;
            dni_paciente.value = data.dni;
            fechaDeAlta.value = data.fechaDeAlta
            domicilioCalle.value = data.domicilio.calle
            domicilioNumero.value = data.domicilio.numero
            domicilioLocalidad.value = data.domicilio.localidad
            domicilioProvincia.value = data.domicilio.provincia
          })
        }
      })
    }

  buscarPorId();

    function actualizarPaciente(){

      update_form.addEventListener('submit', e =>{

        e.preventDefault();

          let payload = {
            id: id_paciente.value,
            nombre : nombre_paciente.value,
            apellido : apellido_paciente.value,
            dni: dni_paciente.value,
            domicilio:{
              calle: domicilioCalle.value,
              numero: domicilioNumero.value,
              localidad: domicilioLocalidad.value,
              provincia: domicilioProvincia.value
            },
            fechaDeAlta: fechaDeAlta.value
        }
        const settings={
          method: 'PUT',
          body: JSON.stringify(payload),
            headers:{
              'Content-type':'application/json'
            }
        }
        const url = `${URL_BASE}/pacientes`
      
        fetch(url,settings)
          .then(response =>{
            return response.json()
          })
          .then(data =>{
            listarPacientes();
          console.log("Respuesta del put");
          console.log(data)

          }).catch(err => {
           console.log("Promesa rechazada:" + err);
          })
      })
    }
    actualizarPaciente();
})