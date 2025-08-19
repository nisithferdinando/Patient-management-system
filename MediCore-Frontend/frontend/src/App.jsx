import React from 'react'
import Routers from "./Routers.jsx";
import ToastifyProvider from "./components/ToastifyProvider.jsx";


const App = () => {
  return (
    <div>
        <Routers/>
        <ToastifyProvider/>
      </div>
  )
}

export default App