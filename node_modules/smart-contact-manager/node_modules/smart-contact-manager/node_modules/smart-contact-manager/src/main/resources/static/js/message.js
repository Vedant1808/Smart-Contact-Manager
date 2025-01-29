const baseURL = "http://localhost:8081";
// const baseURL = "https://www.scm20.site";

document.addEventListener("DOMContentLoaded", function () {
  console.log("message.js");
  const viewMessageModal = document.getElementById("view_message_modal");

  // options with default values
  const options = {
    placement: "bottom-right",
    backdrop: "dynamic",
    backdropClasses: "bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40",
    closable: true,
    onHide: () => {
      console.log("modal is hidden");
    },
    onShow: () => {
      console.log("modal is shown");
    },
    onToggle: () => {
      console.log("modal has been toggled");
    },
  };

  // instance options object
  const instanceOptions = {
    id: "view_message_modal",
    override: true,
  };

  const messageModal = new Modal(viewMessageModal, options, instanceOptions);

  function openMessageModal() {
    messageModal.show();
  }

  function closeMessageModal() {
    messageModal.hide();
  }

  async function loadMessagedata(id) {
    openMessageModal();
    // function call to load data
    console.log(id);

    try {
      const data = await (await fetch(`${baseURL}/api/contacts/${id}`)).json();
      console.log(data);
      document.querySelector("#f_name").value = data.name;
      document.querySelector("#f_email").value = data.email;
    } catch (error) {
      console.log(error);
    }
  }

  async function sendMessagedata() {
    const name = document.querySelector("#f_name").value;
    const sendTo = document.querySelector("#f_email").value;
    const subject = document.querySelector("#f_subject").value;
    const description = document.querySelector("#description").value;
    try {
      await (
        await fetch(
          `${baseURL}/api/contacts/message/${sendTo}/${subject}/${description}`
        )
      ).json();
    } catch (error) {
      console.log(error);
    }
    closeMessageModal();
  }

  window.openMessageModal = openMessageModal;
  window.closeMessageModal = closeMessageModal;
  window.loadMessagedata = loadMessagedata;
  window.sendMessagedata = sendMessagedata;
});
