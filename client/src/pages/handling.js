import Head from 'next/head';
import { Box, Container, Grid, Typography } from '@mui/material';
import { AccountProfile } from '../components/account/account-profile';
import { AccountProfileDetails } from '../components/account/account-profile-details';
import { DashboardLayout } from '../components/dashboard-layout';
import { useContext, useEffect, useState } from 'react';
import { CustomerListToolbar } from '../components/customer/layout-list';
import { LatestOrders } from '../components/dashboard/latest-handlings';
import { v4 as uuid } from 'uuid';
import axios from 'axios';
import { Context } from './_app';


const Page = () => {
  const [search, setSearch] = useState('')
  const { chosedHouse } = useContext(Context)

  useEffect(() => {
    console.log(chosedHouse)
  }, [])
  
  return (
    <>
      <Head>
        <title>
          Оработка заявлений | Bananos House Manager
        </title>
      </Head>
      <Box component="main" sx={{flexGrow: 1, py: 8}}>
        <Container maxWidth={false} style={{gap:'15px', display:'flex', flexDirection:'column'}}>
          <CustomerListToolbar placeholder={'Поиск заявлений'} title='Обработка заявлений' value={search} setValue={setSearch} />
          {chosedHouse && <LatestOrders orders={chosedHouse} search={search} />}
        </Container>
      </Box>
    </>
  )
};

Page.getLayout = (page) => (
  <DashboardLayout>
    {page}
  </DashboardLayout>
);

export default Page;
