import React, { useEffect, useState } from "react";
import axios from "axios";
import '../css/Profile.css';

const Profile = () => {

    const uploadAPI = "http://localhost:9015/profile/upload";
    const watchingAPI = "http://localhost:9015/profile/watching";

    const [files, setFiles] = useState([]);
    const [username, setUsername] = useState('');
    const [profile, setProfile] = useState([]);
    const [userId, setUserId] = useState(null);

    const 파일변경기능 = (e) => {
        //파일을 변경했을 떄 프로필 썸네일에 이미지들 주소가 넘어갈 수 있도록 설정
        const selectedFiles = Array.from(e.target.files);
        console.log(selectedFiles);
        setFiles(selectedFiles);

    }

    /*
    const 유저이름변경 = (e) => {
        setUsername(e.target.value);
    }
    */

    //1. fetch = 설치가 불필요. 리액트 자체에서 제공하는 java 백엔드와 통신하는 기능
    const imageUpload1 = () => {
        const formData = new FormData(); //files 이미지 파일이 여러개이므로 묶어서 보냄
        Array.from(files).forEach(file => {
            formData.append("files", file);
        });
        formData.append("username", username);
        fetch(uploadAPI, {
            method: 'POST', //DB에 값을 저장하기 위한 post
            //headers : {'Content-Type' : 'multipart/form-data'}, //데이터에 파일(이미지)이 포함됨을 자바에 알림
            body: formData
        })
        //DB에 값 넣기를 성공했다면 수행할 작업
        .then(res => {
            //응답에 대한 결과를 json(자바에서 Map일 때) 또는 text(자바에서 String 일때) 형식으로 받음
            return res.text();
        })
        .then(data => {
            //DB에 저장된 프로필 사진과 닉네임을 보여줌 -> 업로드하고 사용자들이 눈치 못채게 새로고침
            getPosts();

        })
    }

    //2. axios (then)
    const imageUpload2 = () => {
        const formData = new FormData(); //files 이미지 파일이 여러개이므로 묶어서 보냄
        Array.from(files).forEach(file => {
            formData.append("files", file);
        });
        formData.append("username", username);

        axios.post(uploadAPI, formData, {
            headers : {'Content-Type' : 'multipart/form-data'}
        })
        .then(res => {
        /* 
            return res.text();
        })
        .then(data => {

        fetch에서는 위 기능이 필요하지만 axios는 불필요
        */
            const data = res.data;
            getPosts();

        })
    }

    //3. axios (await, async) = 2번의 업그레이드 버전. try/catch를 사용해서 오류 처리
    const imageUpload3 = async () => { // async () : 잠시 대기해야할 코드가 적혀있음을 알림
        const formData = new FormData();
        Array.from(files).forEach(file => {
            formData.append("files", file);
        });
        formData.append("username", username);

        //삼항 연산자를 이용해서 수정기능을 위한 url과 새 프로필을 저장할 url 설정

        // formData를 가져오기 전까지 잠시 대기. 정보를 가져오는 것보다 실행이 더 빠를 수 있기 때문
        await axios.post(uploadAPI, formData, {
        })
        .then(res => {
            const data = res.data;
            getPosts();
        })
    }

    const getPosts = async () => {
        await axios.get(watchingAPI)
        .then (res => {
            setProfile(res.data);
        })
    }

    useEffect(() => {
        getPosts();
    },[]);

    //프로필 수정
    const modifying = (p) => {
        setUserId(p.userId); //수정할 사용자 ID 설정
        setUsername(p.username);
    }

    console.log(profile);
    return (
        <div>
            <h1>프로필 이미지 업로드</h1>
            <div className="profile-thumbnail">
                {files.length > 0 && files.map((file, index) => (
                    <div key={index}>
                        <img src={URL.createObjectURL(file)} alt="profile" />
                    </div>
                ))}
            </div>

            <input type="file" onChange={파일변경기능} />
            <input type="text" placeholder="닉네임 입력" value={username} onChange={(e) => setUsername(e.target.value)} />
            {/* 첫 번째 input 방식과 두 번째 input 방식은 똑같은 효과임 */}
            <button onClick={imageUpload3}>프로필 저장</button>
            <hr/>
            <div className="profiles-container">
                {profile && profile.map(post => (
                    <div key={post.userId} className='post'>
                        <h2>{post.username}</h2>
                        <p>{post.createdAt}</p>
                        <div className='images'>
                            {post.profileImageUrl && post.profileImageUrl.split(',').map(image => (
                                <img key={image} src={`http://localhost:9015/images/${image}`} alt='imgFile' />
                            ))}
                        </div>
                        {/* <button onClick={modifying(post)}>수정하기</button>*/}
                    </div>
                ))}
            </div>
        </div>
    )
}
export default Profile;