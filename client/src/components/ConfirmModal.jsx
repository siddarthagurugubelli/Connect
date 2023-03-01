import { Button, Modal, ModalContent, ModalFooter, ModalHeader, ModalOverlay } from "@chakra-ui/react";
import React from "react";
import Footer from "./Footer";

export default function ConfirmModal(props){
console.log(props.open)
    return(
        <>
        <Modal
        isOpen={props.open}
        onClose={props.close}
        >
                <ModalOverlay />
        <ModalContent>
            <ModalHeader>{props.header}</ModalHeader>
            <ModalFooter>
                <Button colorScheme={"red"} onClick={props.close}  mr={3}>No</Button>
                <Button colorScheme={"green"} onClick={props.onSubmit}>Yes</Button>
            </ModalFooter>
        </ModalContent>
        </Modal>
        </>
    )
}