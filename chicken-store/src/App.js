import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import MainRouter from './MainRouter';
import ChickenDetail from './component/ChickenDetail';

function App(){

    return(
        <Router>
            <Routes>
                <Route path='/' element={<MainRouter/>}/>
                <Route path={`/chicken-detail/:id`} element={<ChickenDetail/>}/>
            </Routes>
        </Router>
    )
}
export default App;