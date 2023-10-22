import { format, parseISO } from "date-fns";

export function formatDate(dateTimeString: string) {
  const dateTime = parseISO(dateTimeString);
  return format(dateTime, " dd.MM.yyyy.");
}

export function formatTime(dateTimeString: string | undefined) {
  if (!dateTimeString) {
    return "N/A"; // Or any default string you'd like to display
  }
  const dateTime = parseISO(dateTimeString);
  return format(dateTime, " HH:mm");
}
