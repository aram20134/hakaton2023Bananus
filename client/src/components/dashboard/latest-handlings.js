import { format } from 'date-fns';
import { v4 as uuid } from 'uuid';
import PerfectScrollbar from 'react-perfect-scrollbar';
import {
  Box,
  Button,
  Card,
  CardHeader,
  MenuItem,
  Modal,
  Select,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  TableSortLabel,
  Tooltip,
  Typography
} from '@mui/material';
import ArrowRightIcon from '@mui/icons-material/ArrowRight';
import { SeverityPill } from '../severity-pill';
import { useEffect, useState } from 'react';
import axios from 'axios';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid gray',
  boxShadow: 24,
  p: 4,
};

export const LatestOrders = ({orders, search, setSearch, allReports = false}) => {
  const [open, setOpen] = useState(false)
  
  useEffect(() => {
    console.log(orders)
  }, [])
  
  return (
    <Card>
      <CardHeader title="Последние заявки" />
      <PerfectScrollbar>
        <Box sx={{ minWidth: 800 }}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>
                  id
                </TableCell>
                <TableCell>
                  Клиент
                </TableCell>
                <TableCell>
                  Объект
                </TableCell>
                <TableCell sortDirection="desc">
                  Дата
                </TableCell>
                <TableCell>
                  Статус
                </TableCell>
                {!allReports ? <TableCell></TableCell> : ''}
              </TableRow>
            </TableHead>
            <TableBody>
              {allReports ? (
                orders.reverse().map((order) => (
                  <TableRow hover key={order.id}>
                    <TableCell>
                      {order.id}
                    </TableCell>
                    <TableCell>
                      {order.ownerName}
                      {console.log(order)}
                    </TableCell>
                    <TableCell>
                      {order.houseName}
                    </TableCell>
                    <TableCell>
                      {format(new Date(order.creationDate), 'dd/MM/yyyy')}
                    </TableCell>
                    <TableCell>
                      <Select onChange={(e) => axios.get(`http://10.2.0.84:9091/report_status?id=${order.id}&status=${e.target.value}`)} defaultValue={order.reportStatus}>
                        <MenuItem value={'SOLVED'}>
                          <SeverityPill color={'success'}>
                            Решено
                          </SeverityPill>
                        </MenuItem>
                        <MenuItem value={'IN_PROCESS'}>
                          <SeverityPill color={'warning'}>
                            В обработке
                          </SeverityPill>  
                        </MenuItem>
                        <MenuItem value={'DECLINED'}>
                          <SeverityPill color={'error'}>
                            Отклонено
                          </SeverityPill>   
                        </MenuItem>
                      </Select>
                    </TableCell>
                  </TableRow>
                ))
              ) : (
                orders.reports.filter((ord) => ord.description.toLowerCase().includes(search.toLowerCase())).reverse().map((order) => (
                  <TableRow hover key={order.id}>
                    <TableCell>
                      {order.id}
                    </TableCell>
                    <TableCell>
                      {order.ownerName}
                    </TableCell>
                    <TableCell>
                      {orders.houseName}
                    </TableCell>
                    <TableCell>
                      {format(new Date(order.creationDate), 'dd/MM/yyyy')}
                    </TableCell>
                    <TableCell>
                      <Select onChange={(e) => axios.get(`http://10.2.0.84:9091/report_status?id=${order.id}&status=${e.target.value}`)} defaultValue={order.reportStatus}>
                        <MenuItem value={'SOLVED'}>
                          <SeverityPill color={'success'}>
                            Решено
                          </SeverityPill>
                        </MenuItem>
                        <MenuItem value={'IN_PROCESS'}>
                          <SeverityPill color={'warning'}>
                            В обработке
                          </SeverityPill>  
                        </MenuItem>
                        <MenuItem value={'DECLINED'}>
                          <SeverityPill color={'error'}>
                            Отклонено
                          </SeverityPill>   
                        </MenuItem>
                      </Select>
                    </TableCell>
                    <TableCell align='left'>
                      <Button onClick={() => setOpen(true)} variant='outlined'>Подробно</Button>
                    </TableCell>
                    <Modal
                      open={open}
                      onClose={() => setOpen(!open)}
                      aria-labelledby="modal-modal-title"
                      aria-describedby="modal-modal-description">
                      <Box sx={style}>
                        <Typography id="modal-modal-title" variant="h6" component="h2">
                          Описание жалобы
                        </Typography>
                        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                          {order.description}
                        </Typography>
                        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                          telegram: <a target={'_blank'} href={`https://t.me/${order.ownerName}`}>t.me/{order.ownerName}</a>
                        </Typography>
                      </Box>
                    </Modal>
                  </TableRow>
                ))
              )}
              
            </TableBody>
          </Table>
        </Box>
      </PerfectScrollbar>
      <Box
        sx={{
          display: 'flex',
          justifyContent: 'flex-end',
          p: 2
        }}
      >
        <Button color="primary" endIcon={<ArrowRightIcon fontSize="small" />} size="small" variant="text">
          Все
        </Button>
      </Box>
    </Card>
  )
};
