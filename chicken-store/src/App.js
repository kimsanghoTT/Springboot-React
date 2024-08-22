import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import MainRouter from './MainRouter';
import ChickenDetail from './component/ChickenDetail';

function App(){

    return(
        <Router>
            <Routes>
                {/* Route로 설정된 태그만 들어올 수 있음 */}
                <Route path='/' element={<MainRouter/>}/>
                <Route path={`/chicken-detail/:id`} element={<ChickenDetail/>}/>
            </Routes>
        </Router>
    )
}
export default App;