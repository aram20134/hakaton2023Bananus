import { Button, MenuItem, Modal, Select, TableCell, TableRow, Typography } from "@mui/material"
import { Box } from "@mui/system"
import axios from "axios"
import { format } from "date-fns"
import { useState } from "react"
import { SeverityPill } from "../severity-pill"

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
  
const HandlingTable = ({ order, orders }) => {

    const [open, setOpen] = useState(false)

    return (
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
    )
}

export default HandlingTable