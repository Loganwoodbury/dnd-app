import { useState } from 'react'
import './App.css'
import HomePage from './pages/HomePage/HomePage';
import MonsterPage from './pages/MonsterPage/MonsterPage';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'

function App() {

  return (
    <>
    <BrowserRouter>
    <nav>
      <Link to='/' >Home</Link>&nbsp; | &nbsp;
      <Link to='/Monster'>Monsters</Link>
    </nav>
    <Routes>
      <Route path='/' element={ <HomePage />} />
      <Route path='/monster' element= { <MonsterPage /> } />
      {/* <Route path='/monster/detail/:monsterName' element= { <MonsterDetail /> } /> */}
    </Routes>
    </BrowserRouter>
    </>
  )
}

export default App
