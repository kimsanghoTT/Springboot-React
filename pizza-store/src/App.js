import './App.css';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import PizzaRouter from './components/PizzaRouter';
import SearchResult from './components/SearchPizza';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<PizzaRouter/>}/>
        <Route path='/search' element={<SearchResult/>}/>
      </Routes>
    </Router>
  );
}

export default App;
