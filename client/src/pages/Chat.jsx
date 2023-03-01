import React, { useContext, useEffect, useState } from "react";
import Nav from "../components/Nav";
import { Center, VStack, Flex } from '@chakra-ui/react'
import Header from "../components/Header";
import Divider from "../components/Divider";
import Messages from "../components/Messages";
import Footer from "../components/Footer";
import ChatService from "../services/ChatService";
import AuthContext from "../context/AuthContext";

export default function Chat(){
    const [inputMessage, setInputMessage] = useState("");
    const [messages, setMessages] = useState([])
    
    const chatService = new ChatService()
    const { user } = useContext(AuthContext)

    const handleSendMessage = async() => {
        const obj = {
            "senderId": user.id,
            "message": inputMessage,
            "email": user.email
        }
        await chatService.postMessage(obj, localStorage.getItem("token"))
        setInputMessage("")
        chatService.getAllMessages(localStorage.getItem("token")).then((res) => setMessages(res.data));
    }

    useEffect(() => {
        chatService.getAllMessages(localStorage.getItem("token")).then((res) => setMessages(res.data));
    },[])

    return(
        <>
            <Nav />
            {/* <Center> */}
                <VStack marginTop={'50px'} marginLeft={'100px'}>
                <Flex w="100%" h="100vh" justify="center" align="center">
                    <Flex w={["100%", "100%", "80%"]}  h="90%" flexDir="column">
                        <Header />
                        <Divider />
                        <Messages 
                        messages={messages}
                        setMessages={setMessages}
                        />
                        <Divider />
                        <Footer
                        inputMessage={inputMessage}
                        setInputMessage={setInputMessage}
                        handleSendMessage={handleSendMessage}
                        />
                    </Flex>
                </Flex>
                </VStack>
            {/* </Center> */}
        </>
    )
}