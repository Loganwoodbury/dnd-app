import { useState } from 'react'
import './App.css'
import HomePage from './pages/HomePage/HomePage';
import MonsterPage from './pages/MonsterPage/MonsterPage';
import MonsterDetail from './components/MonsterDetail';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import JournalPage from './pages/JournalPage/JournalPage';

function App() {

  return (
    <>
    <BrowserRouter>
    <nav>
      <Link to='/' >Home</Link>&nbsp; | &nbsp;
      <Link to='/Monster'>Monsters</Link>&nbsp; | &nbsp;
      <Link to='/Journal'>Journal</Link>
    </nav>
    <Routes>
      <Route path='/' element={ <HomePage />} />
      <Route path='/monster' element= { <MonsterPage /> } />
      <Route path='/monster/:monsterName' element= { <MonsterDetail /> } />
      <Route path='/journal' element= { <JournalPage /> } />
    </Routes>
    </BrowserRouter>
    </>
  )
}

export default App
