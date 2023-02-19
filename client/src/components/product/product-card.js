import PropTypes from 'prop-types';
import { Avatar, Box, breadcrumbsClasses, Button, Card, CardContent, CardMedia, CircularProgress, Divider, Grid, LinearProgress, Tooltip, Typography } from '@mui/material';
import { Clock as ClockIcon } from '../../icons/clock';
import { Download as DownloadIcon } from '../../icons/download';
import LightbulbIcon from '@mui/icons-material/Lightbulb';
import { useEffect, useRef, useState } from 'react';
import DoneIcon from '@mui/icons-material/Done';

export const ProductCard = ({ product, ...rest }) => {
  const [color, setColor] = useState('action')
  const [loading, setLoading] = useState(false)
  const [progress, setProgress] = useState(0)
  const [success, setSuccess] = useState(false)
  const videoRef = useRef(null)

  useEffect(() => {
    switch (product.timeLight) {
      case 'Всегда':
        setColor('success')
        break;
      case 'Никогда':
        setColor('error')
        break;
      case '14:00':
        setColor('success')
        break;
      default:
        setColor('action')
        break;
    }
  }, [])

  const startOpen = () => {
    setLoading(true)
    videoRef.current.play()
    const timer = setInterval(() => {
      console.log('first')
      setProgress((oldProgress) => {
        if (oldProgress === 100) {
          setSuccess(true)
          setTimeout(() => {
            setLoading(false)
            setSuccess(false)
            clearTimeout(timer)
            setProgress(0)
          }, 2000);
        }
        const diff = Math.random() * 10;
        return Math.min(oldProgress + diff, 100);
      });
    }, 400);
  }
  
  return (
    <Card
      sx={{
        display: 'flex',
        flexDirection: 'column',
        height: '100%'
      }}
      elevation={6}
      {...rest}
    >
      <CardContent>
        <Typography align="left" color="textPrimary" gutterBottom variant="h5">
          {product.name}
        </Typography>
        <CardMedia ref={videoRef} muted component={'video'} image={product.camera} />
        <Typography marginTop={3} align="left" color="textPrimary" variant="body1">
          {product.description}
        </Typography>
      </CardContent>
      <Box sx={{ flexGrow: 1 }} />
      <Divider />
      <Box sx={{ p: 2 }}>
        <Grid
          container
          spacing={2}
          sx={{ justifyContent: 'space-between' }}
        >
          <Grid direction={'column'} item sx={{alignItems: 'center', display: 'flex', justifyContent:'center'}}>
            <Grid sx={{alignItems: 'center',display: 'flex'}}>
              <ClockIcon color="action" />
              <Tooltip arrow title='Последний вход'>
                <Typography color="black" display="inline" sx={{ pl: 1 }} variant="body2">
                  {product.lastEntered}
                </Typography>
              </Tooltip>
            </Grid>
            <Typography fontSize={12} color={'textSecondary'}>последний вход</Typography>
          </Grid>
          <Grid direction={'column'} item sx={{alignItems: 'center',display: 'flex', justifyContent:'center'}}>
            <Grid sx={{alignItems: 'center',display: 'flex'}}>
              <LightbulbIcon color={color} />
              <Tooltip arrow title='Освещение'>
                <Typography color={color} display="inline" sx={{ pl: 1 }} variant="body2">
                  {product.timeLight}
                </Typography>
              </Tooltip>
            </Grid>
            <Typography fontSize={12} color={'textSecondary'}>освещение</Typography>
          </Grid>
        </Grid>
      </Box>
      <Divider />
      <Box sx={{ p: 2 }}>
        <Grid container spacing={2} sx={{ justifyContent: 'space-between' }}>
          <Grid item sx={{alignItems: 'center', display: 'flex'}}>
            {loading && !success
            ? (<Button disabled onClick={() => startOpen()}><CircularProgress size={'1rem'} /></Button>) 
            : (<Button onClick={() => startOpen()}>{product.type}</Button>)
            }
            {success && <DoneIcon color='success' fontSize='large' />}
          </Grid>
            <Box sx={{ width: '100%', p: loading ? 3 : 0, transition:'all 0.6s ease', opacity: loading ? 1 : 0}}>
              <LinearProgress variant="determinate" value={progress} />
            </Box>
        </Grid>
      </Box>
    </Card>
  )
};

ProductCard.propTypes = {
  product: PropTypes.object.isRequired
};
