import React from "react";
import Board from "./Component/Board";
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import Profile from "./Component/Profile";
import Header from "./Component/Layout/Header";
import Main from "./Component/Main";
import Footer from "./Component/Layout/Footer";
import Banner from "./Component/Layout/Banner";

function App () {

    return(
        <Router>
            <Banner/>
            <Header/>
            <Routes>
                <Route path="/" element={<Main/>}/>
                <Route path="/board" element={<Board/>}/>
                <Route path="/profile" element={<Profile/>}/>
            </Routes>
            <Footer/>
        </Router>
        
    )
}
export default App;

