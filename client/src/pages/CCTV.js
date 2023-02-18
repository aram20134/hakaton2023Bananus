import Head from 'next/head';
import { Box, Card, CardHeader, CardMedia, Container, Grid, Typography } from '@mui/material';
import { DashboardLayout } from '../components/dashboard-layout';
import { SettingsNotifications } from '../components/settings/settings-notifications';
import { SettingsPassword } from '../components/settings/settings-password';
import { CustomerListToolbar } from '../components/customer/layout-list';
import { useState } from 'react';
import styles from '../theme/styles.module.css'

const mockCCTV = [
  {name: 'Двор', floor: 1, video: '/static/videos/camera1.mp4'},
  {name: 'Вход', floor: 1, video: '/static/videos/camera4.mp4'},
  {name: 'Приёмная', floor: 1, video: '/static/videos/camera2.mp4'},
  {name: 'Офис 1', floor: 2, video: '/static/videos/camera3.mp4'},
  {name: 'Офис 2', floor: 2, video: '/static/videos/camera5.mp4'}
]

const Page = () => {
  const [search, setSearch] = useState('')

  return (
    <>
      <Head>
        <title>
          Видеонаблюдение | Bananos House Manager
        </title>
      </Head>
      <Box component="main" sx={{flexGrow: 1, py: 8}}>
        <Container maxWidth={false}>
          <CustomerListToolbar placeholder={'Поиск камер'} title='Видеонаблюдение' value={search} setValue={setSearch} />
          <Grid style={{marginTop:'1rem'}} container spacing={3}>
            {mockCCTV.filter((pl) => pl.name.toLowerCase().includes(search.toLowerCase())).map((CCTV) => 
            <Grid item lg={3} sm={6} xl={6} xs={12}>
              <Card className={styles.animate}>
                <CardHeader subheader={`Этаж ${CCTV.floor}`}  title={CCTV.name} />
                <CardMedia muted autoPlay={true} component={'video'} image={CCTV.video} />
              </Card>
            </Grid>
            )}
          </Grid>
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
