import json

import requests
from aiogram import Bot, Dispatcher, types
from aiogram.utils import executor

dict = {"0":{"status": 0, "regist": 0}}
username = ""
#status = 0
#regist = 0

headers = {
    "Content-Type": "application/json",
    "Accept": "text/plain"
}

TOKEN = '6003769742:AAGDozS2vCKXAN_g55RHgRUb0YgFnDMbfAU'

bot = Bot(token=TOKEN)
dp = Dispatcher(bot)


@dp.message_handler(commands=['start'])
async def process_start_command(message: types.Message):
    keyboard = types.ReplyKeyboardMarkup(resize_keyboard=True)

    user_id = message.from_user.id
    response = requests.get(f"http://10.2.0.84:9091/tguser?tgId={user_id}")

    #global regist
    global dict
    id = dict['message.from_user.id']

    if not response.text == "":
        regist = 1

    if regist == 1:
        buttons = ["Написать заявку", "Запрос квитанции"]
        keyboard.add(*buttons)
    else:
        buttons = ["Регистрация"]
        keyboard.add(*buttons)

    await message.reply("Привет! Есть вопросы?", reply_markup=keyboard)


@dp.message_handler(commands=['help'])
async def process_help_command(message: types.Message):
    await message.reply("Открой меню кнопок и выбери желаемое действие")


@dp.message_handler()
async def msg(message: types.Message):
    #global status

    keyboard = types.ReplyKeyboardMarkup(resize_keyboard=True)
    #global regist

    if message.text == "Написать заявку":
        await bot.send_message(message.chat.id, "Напишите суть проблемы, и введите номер телефона")
        status = 10
    elif status == 10:
        status = 0
        print(message.text)
    elif message.text == "Запрос квитанции":
        response = requests.get(f"http://10.2.0.84:9091/services?id={message.from_user.id}")
        data = response.json()
        gas = data["gas"]
        heating = data["heating"]
        water = data["water"]
        electricity = data["electricity"]

        await bot.send_message(message.chat.id, f"Газ:{gas}\nГорячая вода:{heating}\nХолодная вода:{water}\nЭлектричество:{electricity}")

    elif message.text == "Регистрация":
        status = 1
        await bot.send_message(message.chat.id, "Введите название объекта инфраструктуры\nПример: Аквариум")
    elif status == 1:

        body = {
            "telegramId": message.from_user.id,
            "telegramUsername": message.from_user.username,
            "complex": message.text,
        }

        response = requests.post("http://10.2.0.84:9091/complex", headers=headers, data=json.dumps(body))

        if (response.text == "OK"):
            status = 2
            await bot.send_message(message.chat.id, "Номер квартиры/офиса/помещеня")
        else:
            await bot.send_message(message.chat.id, "Объект инфроструктуры не найден")

    elif status == 2:
        buttons = ["Написать обращение", "Запрос квитанции"]
        keyboard.add(*buttons)

        body = {
            "telegramId": message.from_user.id,
            "telegramUsername": message.from_user.username,
            "apartment": message.text
        }

        requests.post("http://10.2.0.84:9091/register", headers=headers, data=json.dumps(body))
        status = 0
        await message.reply("Ваша заявка отправлена на рассмотрение, мы свяжемся в кратчайшие сроки!", reply_markup=keyboard)


if __name__ == '__main__':
    executor.start_polling(dp)