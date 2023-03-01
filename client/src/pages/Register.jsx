import { Button, Container, Flex, FormControl, FormLabel, Heading, Image, Input, InputGroup, InputRightAddon, InputRightElement, Stack, Text, Tooltip, useBreakpointValue, useToast, VStack } from '@chakra-ui/react'
import { useFormik } from 'formik'
import React, { useContext } from 'react'
import { useState } from 'react'
import { createRoutesFromChildren, Link, useNavigate } from 'react-router-dom'
import AuthContext from '../context/AuthContext'
import AuthService from '../services/AuthService'
import svg from '../svgs/main.svg'
import { InfoIcon, ViewIcon, ViewOffIcon } from '@chakra-ui/icons'

function Register() {
  const [showPassword , setShowPassword]= useState(false)
  const authService = new AuthService();
  const toast = useToast()
  const {login} = useContext(AuthContext)
  const navigate = useNavigate()
  const passwordInfo=`Password should contain one uppercase letter
  Password should contain one lowercase letter
  Password should contain one digit
   one special character and minimum length should be 8`
  const [inputFields, setInputFields] = useState({
    firstName: "",
    lastName: "",
    email: "",
    pswrd: ""
  })

  const [inputFieldsErr, setInputFieldsErr] = useState({
    firstNameErr: "",
    lastNameErr: "",
    emailErr: "",
    pswrdErr: ""
  })

  const isInputValid = (value, regex) => {
    const reg = value.match(regex);
    const isEmpty = value === ""
    return reg === null || isEmpty ? false : true
  }

  const handleInputChanges = {
    firstNameChange: (value) => {
      setInputFields({...inputFields, firstName: value})
      setInputFieldsErr({...inputFieldsErr, firstNameErr: isInputValid(value, /^[A-Za-z]{3,}$/) ? "" : "Invalid Name format"})
    },
    lastNameChange: (value) => {
      setInputFields({...inputFields, lastName: value})
      setInputFieldsErr({...inputFieldsErr, lastNameErr: isInputValid(value, /^[A-Za-z]{3,}$/) ? "" : "Invalid Name format"})
    },
    emailChange: (value) => {
      setInputFields({...inputFields, email: value})
      setInputFieldsErr({...inputFieldsErr, emailErr: isInputValid(value, /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/) ? "" : "Invalid Email format"})
    },
    pswrdChange: (value) => {
      setInputFields({...inputFields, pswrd: value})
      setInputFieldsErr({...inputFieldsErr, pswrdErr: isInputValid(value, /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/) ? "" : "Invalid Password format"})
    }
  }
  const isErr = Object.keys(inputFieldsErr)?.some(key => inputFieldsErr[key] !== "")

  const handleSubmit = async() => {
    if(!isErr){
      const obj = {
        name: inputFields.firstName,
        lastName: inputFields.lastName,
        email: inputFields.email,
        password: inputFields.pswrd
      }
      await authService.register(obj).then(() => {
        navigate("/login")
      }).catch((err) => {
          toast({
            title: err?.response?.data || "Invalid details",
            status: 'error',
            duration: 1000,
            isClosable: true,
          })
      })

    }
  }

  return (
    <Stack direction={'row'} spacing={0} minH={'100vh'}>
      <Flex alignItems={'center'} justifyContent={'center'} width={{ base: 0, md: '100%', lg: '100%' }}>
        <VStack p={10} spacing={5}>
          <Stack spacing={6} w={'full'} maxW={'lg'}>
            <Heading fontSize={{ base: '0', md: '5xl', lg: '6xl' }}>
              <Text
                as={'span'}
                position={'relative'}
                _after={{
                  content: "''",
                  width: 'full',
                  height: useBreakpointValue({ base: '20%', md: '30%' }),
                  position: 'absolute',
                  bottom: 1,
                  left: 0,
                  bg: 'blue.500',
                  zIndex: -1,
                }}>
                CONNECT
              </Text>
              <br />{' '}
              <Text color={'blue.500'} as={'span'} fontSize={"40px"} fontStyle={"italic"}>
                  Frame your social space
              </Text>{' '}
            </Heading>
          </Stack>
          <Image src={svg} />
        </VStack>
      </Flex>
      <Flex justifyContent={'center'} alignItems={'center'} width={'100%'}>
        <Container>
          <VStack as={'form'} p={10} borderRadius={'xl'} boxShadow={'2xl'} spacing={5}>
            <Heading>Register</Heading>
            <FormControl>
              <FormLabel>First Name</FormLabel>
              <Input
                onChange={(e) => handleInputChanges.firstNameChange(e.target.value)}
                value={inputFields.firstName}
                name='firstName'
                type='text'
              />
              <span className="error">{inputFieldsErr.firstNameErr}</span>

            </FormControl>
            <FormControl>
              <FormLabel>Last Name</FormLabel>
              <Input
                onChange={(e) => handleInputChanges.lastNameChange(e.target.value)}
                value={inputFields.lastName}
                name='lastName'
                type='text'
              />
              <span className="error">{inputFieldsErr.lastNameErr}</span>

            </FormControl>
            <FormControl>
              <FormLabel>Email address</FormLabel>
              <Input
                onChange={(e) => handleInputChanges.emailChange(e.target.value)}
                value={inputFields.email}
                name='email'
                type='email' />
              <span className="error">{inputFieldsErr.emailErr}</span>

            </FormControl>
            <FormControl>
              <Flex >
              <FormLabel mr={"5px"} >Password</FormLabel>
              <Tooltip placement="right" label={passwordInfo} fontSize='sm' >
               <InfoIcon mt={"5px"} cursor={"pointer"}/> 
              </Tooltip>
              </Flex>
              <InputGroup>
              <Input
                onChange={(e) => handleInputChanges.pswrdChange(e.target.value)}
                value={inputFields.pswrd}
                name='password'
                type={showPassword? 'text' : 'password'}
                />
                <InputRightElement
                  children={showPassword ? <ViewOffIcon onClick={() => setShowPassword(false)} /> : <ViewIcon onClick={() => setShowPassword(true)} />}
                />
                </InputGroup>
              <span className="error">{inputFieldsErr.pswrdErr}</span>

            </FormControl>
            <Flex justifyContent={"space-between"} width={"100%"}>
            <Button as={Link} to={"/login"}>Login</Button>
            <Button colorScheme={'blue'} onClick={handleSubmit}
              isDisabled={Object.keys(inputFields).some(key =>inputFields[key]==="") || isErr  }
            >Register</Button>
            
           </Flex>
          </VStack>
        </Container>
      </Flex>
    </Stack>
  )
}

export default Register