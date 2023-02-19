import json

import requests
from aiogram import Bot, Dispatcher, types
from aiogram.utils import executor

dict = {"0":{"status": 0, "regist": 0}}
username = ""

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

    global dict

    if f'{user_id}' not in dict:
        dict[f'{user_id}'] = {"status": 0, "regist": 0}

    if not response.text == "":
        dict[f'{message.from_user.id}']['regist'] = 1

    if dict[f'{message.from_user.id}']['regist'] == 1:
        buttons = ["Написать заявку", "Запрос квитанции"]
        keyboard.add(*buttons)
    else:
        buttons = ["Регистрация"]
        keyboard.add(*buttons)

    await message.reply("Привет! Чем могу помочь?", reply_markup=keyboard)


@dp.message_handler(commands=['help'])
async def process_help_command(message: types.Message):
    await message.reply("Открой меню кнопок и выбери желаемое действие")


@dp.message_handler()
async def msg(message: types.Message):
    global dict

    if f'{message.from_user.id}' not in dict:
        dict[f'{message.from_user.id}'] = {"status": 0, "regist": 0}

    print(dict)
    keyboard = types.ReplyKeyboardMarkup(resize_keyboard=True)

    if message.text == "Запрос квитанции":
        response = requests.get(f"http://10.2.0.84:9091/services?id={message.from_user.id}")
        data = response.json()
        gas = data["gas"]
        heating = data["heating"]
        water = data["water"]
        electricity = data["electricity"]

        await bot.send_message(message.chat.id,f"Газ: {gas}\nГорячая вода: {heating} m³\nХолодная вода: {water} m³\nЭлектричество: {electricity} кВт⋅ч")
        dict[f'{message.from_user.id}']['status'] = 0

    elif message.text == "Написать обращение":
        await bot.send_message(message.chat.id, "Напишите суть проблемы и мы постораемся решить её")
        dict[f'{message.from_user.id}']['status'] = 10
    elif dict[f'{message.from_user.id}']['status'] == 10:
        dict[f'{message.from_user.id}']['status'] = 0

        body = {
            "telegramId": message.from_user.id,
            "description": message.text
        }

        requests.post("http://10.2.0.84:9091/report", data=json.dumps(body), headers=headers)
        print(message.text)
        await message.reply("Ваша заявка отправлена на рассмотрение, мы свяжемся в кратчайшие сроки!")

    elif message.text == "Регистрация":
        dict[f'{message.from_user.id}']['status'] = 1
        await bot.send_message(message.chat.id, "Введите название объекта инфраструктуры\nПример: Аквариум")
    elif dict[f'{message.from_user.id}']['status'] == 1:

        body = {
            "telegramId": message.from_user.id,
            "telegramUsername": message.from_user.username,
            "complex": message.text.upper(),
        }

        response = requests.post("http://10.2.0.84:9091/complex", headers=headers, data=json.dumps(body))

        if (response.text == "OK"):
            dict[f'{message.from_user.id}']['status'] = 2
            await bot.send_message(message.chat.id, "Номер квартиры/офиса/помещеня")
        else:
            await bot.send_message(message.chat.id, "Объект инфраструктуры не найден")

    elif dict[f'{message.from_user.id}']['status'] == 2:
        buttons = ["Написать обращение", "Запрос квитанции"]
        keyboard.add(*buttons)

        body = {
            "telegramId": message.from_user.id,
            "telegramUsername": message.from_user.username,
            "apartment": message.text
        }

        requests.post("http://10.2.0.84:9091/register", headers=headers, data=json.dumps(body))
        dict[f'{message.from_user.id}']['status'] = 0
        await message.reply("Вы успешно зарегистрированы", reply_markup=keyboard)


if __name__ == '__main__':
    executor.start_polling(dp)