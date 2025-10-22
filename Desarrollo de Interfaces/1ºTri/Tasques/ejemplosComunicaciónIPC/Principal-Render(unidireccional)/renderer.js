const counter = document.getElementById('counter');

window.electronAPI.onUpdateCounter((_event,value) => {
  const oldValue = Number(counter.innerText);
  const newValue = oldValue + value;
  counter.innerText = newValue.toString();
});