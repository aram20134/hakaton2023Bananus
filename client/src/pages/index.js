import Head from 'next/head';
import { Box, CircularProgress, Container, Grid } from '@mui/material';
import { Water } from '../components/dashboard/water';
import { LatestOrders } from '../components/dashboard/latest-handlings';
import { LatestProducts } from '../components/dashboard/latest-products';
import { Sales } from '../components/dashboard/sales';
import { TasksProgress } from '../components/dashboard/tasks-progress';
import { TotalCustomers } from '../components/dashboard/total-customers';
import { TotalProfit } from '../components/dashboard/total-objects';
import { TrafficByDevice } from '../components/dashboard/traffic-objects';
import { DashboardLayout } from '../components/dashboard-layout';
import mapboxgl from '!mapbox-gl'; // or "const mapboxgl = require('mapbox-gl');"
import { useContext, useEffect, useRef, useState } from 'react';
import axios, { all } from 'axios';
import { v4 as uuid } from 'uuid';
import { Context } from './_app';
 
mapboxgl.accessToken = 'pk.eyJ1IjoiYXJhMTIzMzEiLCJhIjoiY2xlOW94cXcxMDNhdTNuang3ZGtldXU1ayJ9.g_svetyL4qXjEDuZIs-ndg';

const Page = () => {
  const map = useRef(null)
  const mapContainer = useRef(null)
  const { houses } = useContext(Context)
  const [allReports, setAllReports] = useState([])
  const [bounds, setBounds] = useState([])
  
  useEffect(() => {
    if (map.current) return 
    const res = houses.reduce((acc, cur) => {
      acc.push([cur.latitude, cur.longtitude])
      return acc
    }, [])
    console.log('res', res)
    setBounds(new mapboxgl.LngLatBounds(...res))
    
    map.current = new mapboxgl.Map({
      container: mapContainer.current,
      style: 'mapbox://styles/mapbox/streets-v12', // style URL
      zoom: 11, // starting szosoms
      center: [39, 45.06]
    })
    // .fitBounds(bounds)

  }, [houses])

  useEffect(() => {
    if (!map.current) return
    // new mapboxgl.Marker().setLngLat([45, 39]).setPopup(new mapboxgl.Popup().setHTML("<h1>Hello World!</h1>")).addTo(map.current)
    const progressMarkers = houses.reduce((acc, cur) => {
      var res = cur.reports.filter((mark) => mark.reportStatus === "IN_PROCESS")
      if (res.length) {
        acc.push(...res)
      }
      return acc
    }, [])
    houses?.map((h) => {
      if (progressMarkers.filter((mark) => mark.houseName === h.houseName).length) {
        return new mapboxgl.Marker({color:'red'}).setLngLat([h.longtitude, h.latitude]).setPopup(new mapboxgl.Popup().setHTML(`<div style={{display:'flex', flexDirection:'column', gap:'15px'}}><h2>${h.houseName}</h2><p>Улица: ${h.address}</p><p>необработанных заявок: ${progressMarkers.filter((mark) => mark.houseName === h.houseName).length}</p><a href="/handling">Перейти к заявкам</a></div>`)).addTo(map.current)
      } else {
        return new mapboxgl.Marker({}).setLngLat([h.longtitude, h.latitude]).setPopup(new mapboxgl.Popup().setHTML(`<div style={{display:'flex', flexDirection:'column', gap:'15px'}}><h2>${h.houseName}</h2><p>Улица: ${h.address}</p></div>`)).addTo(map.current)
      }
    })
  }, [map, houses])

  useEffect(() => {
    
    axios.get('http://10.2.0.84:9091/allreports').then(({data}) => setAllReports(data))
  }, [])  

  return (
  <>
    <Head>
      <title>
        Главная | Bananos House Manager
      </title>
    </Head>
    <Box component="main" sx={{flexGrow: 1,py: 8}}>
      <Container maxWidth={false}>
        <Grid container spacing={3}>
          <Grid item lg={3} sm={6} xl={3} xs={12}>
            <Water value={houses.length === 0 ? <CircularProgress /> : '0082347'} type={'hot'} />
          </Grid>
          <Grid item xl={3} lg={3} sm={6} xs={12}>
            <Water value={houses.length === 0 ? <CircularProgress /> : '0363910'} type={'cold'} />
          </Grid>
          <Grid item xl={3} lg={3} sm={6} xs={12}>
            <Water value={houses.length === 0 ? <CircularProgress /> : '031845'} type={'gas'} />
          </Grid>
          <Grid item xl={3} lg={3} sm={6} xs={12}>
            <TotalProfit value={houses.length === 0 ? <CircularProgress /> : houses.length} sx={{ height: '100%' }} />
          </Grid>
          <Grid item lg={8} md={12} xl={9} xs={12}>
            <div ref={mapContainer} style={{width:'100%', height:'100%', minWidth:'200px', minHeight:'300px'}}></div>
          </Grid>
          <Grid item lg={4} md={6} xl={3} xs={12}>
            <TrafficByDevice sx={{ height: '100%' }} />
          </Grid>
          {/* <Grid item lg={4} md={6} xl={3} xs={12}>
            <LatestProducts sx={{ height: '100%' }} />
          </Grid> */}
          <Grid item lg={8} md={12} xl={9} xs={12}>
            {allReports && <LatestOrders search={''} orders={allReports} allReports={true} />}
          </Grid>
        </Grid>
      </Container>
      {/* {map} */}
    </Box>
  </>
)};

Page.getLayout = (page) => (
  <DashboardLayout>
    {page}
  </DashboardLayout>
);

export default Page;
