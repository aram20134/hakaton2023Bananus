import Head from 'next/head';
import NextLink from 'next/link';
import Router from 'next/router';
import { Box, Button, Card, CardContent, CardHeader, Container, Grid, Link, TextField, Typography } from '@mui/material';
import { CustomerListToolbar } from '../components/customer/layout-list';
import { useContext, useState } from 'react';
import { DashboardLayout } from '../components/dashboard-layout';
import { Context } from './_app';
import { Water } from '../components/dashboard/water';
import styles from '../theme/styles.module.css'

const Login = () => {
  const [search, setSearch] = useState('')
  const { houses } = useContext(Context)

  function randomInteger(min, max) {
    // получить случайное число от (min-0.5) до (max+0.5)
    let rand = min - 0.5 + Math.random() * (max - min + 1);
    return Math.round(rand);
  }

  return (
    <>
      <Head>
        <title>
          Показание счётчиков | Bananos House Manager
        </title>
      </Head>
      <Box component="main" sx={{flexGrow: 1, py: 8}}>
        <Container maxWidth={false}>
          <CustomerListToolbar placeholder={'Поиск объектов'} title='Счётчики' value={search} setValue={setSearch} />
          <Grid style={{marginTop:'1rem'}} container spacing={3}>
            {houses.filter((h) => h.houseName.toLowerCase().includes(search.toLowerCase())).map((h) => 
              <Grid item lg={14} sm={6} xl={6} xs={12}>
                <Card className={styles.animate}>
                  <CardHeader subheader={h.address}  title={h.houseName} />
                  <CardContent >
                    <Grid item lg={12} sm={6} xl={6} xs={12} style={{gap:'25px', display:'flex'}}>
                      <Water value={randomInteger(230, 1500)} type={'gas'} />
                      <Water value={randomInteger(230, 1500)} type={'cold'} />
                      <Water value={randomInteger(230, 1500)} type={'hot'} />
                    </Grid>
                  </CardContent>
                </Card>
              </Grid>
            )}
          </Grid>
        </Container>
      </Box>
    </>
  );
};
Login.getLayout = (page) => (
  <DashboardLayout>
    {page}
  </DashboardLayout>
);

export default Login;
