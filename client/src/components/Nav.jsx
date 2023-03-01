import {
  Box,
  useColorModeValue,
  Stack,
  Button,
  useDisclosure,
} from '@chakra-ui/react';
import { useState } from 'react';
import { useContext } from 'react';
import { BiHome } from 'react-icons/bi'
import { FaRocketchat } from "react-icons/fa";
import {CgProfile} from 'react-icons/cg'
import AuthContext from '../context/AuthContext';
import AddPost from '../pages/AddPost';
import ConfirmModal from './ConfirmModal';
import NavItem from './NavItem';
import {AiFillContacts} from "react-icons/ai"


function Nav() {
  const {user,logout} = useContext(AuthContext)
  const { isOpen, onOpen, onClose } = useDisclosure()

  return (
    <>
    <Box top={{lg:4}} zIndex={1} w={{ sm: "100%", lg: '30vh' }} position={{ sm:'sticky', lg: 'fixed' }} px={5}>
      <Stack
        bg={'white'}
        color={useColorModeValue('gray.600', 'white')}
        borderRadius={"2xl"}
        spacing={'10'}
        p={'15px'}
        h={{ sm: '20', lg: '95vh' }}
        direction={{ sm: 'row', lg: 'column' }}
        boxShadow={'2xl'}
      >
        <NavItem description={'Home'} icon={<BiHome/>} path={"/home"}/>
        <NavItem description={'Profile'} icon={<CgProfile/>} path={`/profile/${user.id}`}/>
        <NavItem description={'Accounts'} icon={<AiFillContacts />} path={"/accounts"} />
        <NavItem description={'Chat'} icon={<FaRocketchat />} path={"/chat"} />
        <AddPost/>
        <Button onClick={onOpen}>Logout</Button>
        
      </Stack>
    </Box>
    { isOpen && <ConfirmModal
         open={isOpen}
         close={onClose}
         onSubmit={logout}
         header={"Are you sure?"}
        />}
    </>
  )
}

export default Nav