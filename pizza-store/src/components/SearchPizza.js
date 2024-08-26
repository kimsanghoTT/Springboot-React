import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

const SearchResult = () => {
    //검색된 피자들을 담을 변수명 설정
    const [pizzas, setPizzas] = useState([]);

    //useLocation : 현재 페이지의 정보를 가지고 있는 함수. (경로, 검색된 문자 등)
    //pathname, search, hash, state, key가 존재.
    const location = useLocation(); // 컴퓨터 상에서 내 위치 정보를 변수에 담음

    //정보가 담긴 변수 안에서 특정 키의 값을 가지고 오는 것
    //검색어 변수로는 주로 keyword, searchTerm, query
    const query = new URLSearchParams(location.search).get("query");

    //검색에 대한 정보를 바로 보여주고 검색어가 수정되면 재 검색을 해야함
    useEffect(() => {
        if (query) {
            axios.get(`http://localhost:9091/api/pizza/search?query=${query}`)
                .then(res => {
                    setPizzas(res.data);
                })
        }
    }, [query])

    //검색 관련 변수, 기능
    const [searchTerm, setSearchTerm] = useState('');
    const navigate = useNavigate();
    const handleSearch = () => {
        navigate(`/search?query=${searchTerm}`);
        setSearchTerm('');
    }

    console.log(pizzas.length);
    console.log(pizzas);


    return (
        <div className="pizza-search-list">
            <h1>검색 결과 : "{query}"</h1>
            {pizzas.length > 0 ?
                pizzas.map(pizza => (
                    <div key={pizza.id}>
                        <h2>{pizza.name}</h2>
                        <p>{pizza.description}</p>
                        <p>{pizza.price}</p>
                    </div>
                )) :
                <div>
                    <p>검색 결과가 없습니다.</p>
                </div>
            }
            <h1>피자 메뉴 검색하기</h1>
            <div className="search-container">
                <input type="text" placeholder="검색하고 싶은 치킨 메뉴를 작성해주세요"
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                />
                <button onClick={handleSearch}>검색하기</button>
            </div>
        </div>
    )
}
export default SearchResult;